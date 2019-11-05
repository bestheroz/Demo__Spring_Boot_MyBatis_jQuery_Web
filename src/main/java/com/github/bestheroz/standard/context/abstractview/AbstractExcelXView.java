package com.github.bestheroz.standard.context.abstractview;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.file.excel.ExcelVO;
import com.github.bestheroz.standard.common.util.MyDateUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component("AbstractExcelXView")
public abstract class AbstractExcelXView extends AbstractView {
    /**
     * The extension to look for existing templates
     */
    public static final String EXTENSION = ".xlsx";
    public static final String FILE_NAME = "fileName";
    public static final String PASSWORD = "password";
    public static final String EXCEL_VOS = "excelVOs";
    public static final String SQL = "SQL";
    public static final String LIST_DATA = "listData";
    /**
     * The content type for an Excel response
     */
    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String ROW_NUMBER = "ROW_NUMBER";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private XSSFCellStyle stringRightStyle;
    private XSSFCellStyle stringCenterStyle;
    private XSSFCellStyle numberStyle;
    private XSSFCellStyle doubleStyle;
    private XSSFCellStyle dateStyle;

    /**
     * Default Constructor. * Sets the content type of the view to "application/vnd.ms-excel".
     */
    public AbstractExcelXView() {
        this.setContentType(CONTENT_TYPE);
    }

    public static void addHeaderOfRowNo(final List<ExcelVO> excelVOList, final String title) {
        addHeader(excelVOList, title, ROW_NUMBER, AbstractExcelXView.CellType.STRING_CENTER, null);
    }

    public static void addHeader(final List<ExcelVO> excelVOList, final String title, final String dbColName, final CellType cellType) {
        addHeader(excelVOList, title, dbColName, cellType, null);
    }

    public static void addHeader(final List<ExcelVO> excelVOList, final String title, final String dbColName, final CellType cellType, final JsonElement codeObject) {
        final ExcelVO excelVO = new ExcelVO();
        excelVO.setTitle(title);
        excelVO.setDbColName(dbColName);
        excelVO.setCellType(cellType);
        excelVO.setCodeObject(codeObject);
        if (cellType.equals(CellType.STRING)) {
            excelVO.setCharByte(1.2D);
        }
        excelVOList.add(excelVO);
    }

    // 엑셀 암호화 때문에 별도 구현
    // @Override
    // protected void writeToResponse(final HttpServletResponse response, final ByteArrayOutputStream baos) throws IOException {
    // // Write content type and also length (determined via byte array).
    // response.setContentType(this.getContentType());
    // response.setContentLength(baos.size());
    //
    // // Flush byte array to servlet output stream.
    // ServletOutputStream out = response.getOutputStream();
    // baos.writeTo(out);
    // out.flush();
    // }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    /**
     * Renders the Excel view, given the specified model.
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    protected final void renderMergedOutputModel(final Map<String, Object> model, final HttpServletRequest request, final HttpServletResponse response) {
        // java.lang.OutOfMemoryError: Java heap space 발생시...
        // -XX:PermSize=64M -XX:MaxPermSize=1000M
        try (final ByteArrayOutputStream baos = this.createTemporaryOutputStream(); final SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);

            this.stringRightStyle = (XSSFCellStyle) workbook.createCellStyle();
            this.stringRightStyle.setAlignment(HorizontalAlignment.RIGHT);

            this.stringCenterStyle = (XSSFCellStyle) workbook.createCellStyle();
            this.stringCenterStyle.setAlignment(HorizontalAlignment.CENTER);

            this.numberStyle = (XSSFCellStyle) workbook.createCellStyle();
            this.numberStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("#,##0"));

            this.doubleStyle = (XSSFCellStyle) workbook.createCellStyle();
            this.doubleStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("#,##0.00"));

            this.dateStyle = (XSSFCellStyle) workbook.createCellStyle();
            this.dateStyle.setAlignment(HorizontalAlignment.CENTER);

            this.logger.debug("Created Excel Workbook from scratch");

            this.buildExcelDocument(model, workbook, request, response);

            this.logger.debug("Excel Password : {}", model.get(PASSWORD));
            if (StringUtils.isNotEmpty((String) model.get(PASSWORD))) {
                final POIFSFileSystem fs = new POIFSFileSystem();
                final EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
                final Encryptor enc = info.getEncryptor();
                enc.confirmPassword(String.valueOf(model.get(PASSWORD)));
                final OutputStream os = enc.getDataStream(fs);
                workbook.write(os);
                final ServletOutputStream out = response.getOutputStream();
                fs.writeFilesystem(out);
                response.setContentType(this.getContentType());
                // response.setContentLength(baos.size());
                out.flush();
            } else {
                workbook.write(baos);
                this.writeToResponse(response, baos);
            }
        } catch (final Throwable e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            response.setContentType("text/html;charset=utf-8");
            try (final PrintWriter pw = response.getWriter()) {
                pw.println("<script>");
                pw.println("alert('파일이 없습니다.');");
                pw.println("history.back();");
                pw.println("</script>");
            } catch (final IOException e1) {
                this.logger.warn(ExceptionUtils.getStackTrace(e1));
                throw new CommonException(e1);
            }
        }
    }

    protected SXSSFWorkbook getTemplateSource(final String url, final HttpServletRequest request) throws Exception {
        final ApplicationContext applicationContext = this.getApplicationContext();
        if (applicationContext == null) {
            this.logger.warn("applicationContext is null");
            throw CommonException.EXCEPTION_ERROR_SYSTEM;
        }
        final LocalizedResourceHelper helper = new LocalizedResourceHelper(applicationContext);
        final Locale userLocale = RequestContextUtils.getLocale(request);
        final Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);

        // Create the Excel document from the source.
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Loading Excel workbook from {}.", inputFile);
        }
        // POIFSFileSystem fs = new POIFSFileSystem(inputFile.getInputStream());
        return new SXSSFWorkbook(new XSSFWorkbook(inputFile.getInputStream()));
    }

    protected abstract void buildExcelDocument(Map<String, Object> model, SXSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception;

    protected SXSSFCell getCell(final SXSSFSheet sheet, final int row, final int col) {
        SXSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        SXSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }

    protected void autoSizeColumn(final SXSSFSheet sheet, final List<ExcelVO> excelVOs) {
        sheet.trackAllColumnsForAutoSizing();
        for (int j = 0; j < excelVOs.size(); j++) {
            sheet.autoSizeColumn(j);
            // java.lang.IllegalArgumentException: The maximum column width for an individual cell is 255 characters. max 59999
            int columWidth = (int) (sheet.getColumnWidth(j) * excelVOs.get(j).getCharByte() + 300);
            columWidth = Math.max(columWidth, 3000);
            columWidth = Math.min(columWidth, 59999);
            sheet.setColumnWidth(j, columWidth);
        }
        sheet.untrackAllColumnsForAutoSizing();
    }

    protected void writeColumnData(final List<ExcelVO> excelVOs, final Integer columnIdx, final SXSSFCell cell, final String data) {
        String strData = data;
        if (excelVOs.get(columnIdx).getCellType().equals(CellType.INTEGER)) {
            this.setInteger(cell, strData);
        } else if (excelVOs.get(columnIdx).getCellType().equals(CellType.DOUBLE)) {
            this.setDouble(cell, strData);
        } else if (excelVOs.get(columnIdx).getCellType().equals(CellType.DATE) || excelVOs.get(columnIdx).getCellType().equals(CellType.DATE_YYYYMMDDHHMMSS)) {
            strData = RegExUtils.removeAll(strData, "\\.0");
            if (StringUtils.isNumeric(strData)) {
                strData = MyDateUtils.getString(Long.parseLong(strData), MyDateUtils.YYYY_MM_DD_HH_MM_SS);
            }
            this.setDate(cell, strData);
        } else if (excelVOs.get(columnIdx).getCellType().equals(CellType.DATE_YYYYMMDD)) {
            strData = RegExUtils.removeAll(strData, "\\.0");
            if (StringUtils.isNumeric(strData)) {
                strData = MyDateUtils.getString(Long.parseLong(strData), MyDateUtils.YYYY_MM_DD);
            }
            this.setDate(cell, strData);
        } else {
            try {
                if (MyNullUtils.isNotEmpty(excelVOs.get(columnIdx).getCodeObject()) && excelVOs.get(columnIdx).getCodeObject().getAsJsonObject().get(strData) != null) {
                    if (excelVOs.get(columnIdx).getCellType().equals(CellType.STRING_CENTER)) {
                        this.setStringCenter(cell, excelVOs.get(columnIdx).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    } else if (excelVOs.get(columnIdx).getCellType().equals(CellType.STRING_RIGHT)) {
                        this.setStringRight(cell, excelVOs.get(columnIdx).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    } else {
                        this.setString(cell, excelVOs.get(columnIdx).getCodeObject().getAsJsonObject().get(strData).getAsString());
                    }
                } else {
                    if (excelVOs.get(columnIdx).getCellType().equals(CellType.STRING_CENTER)) {
                        this.setStringCenter(cell, strData);
                    } else if (excelVOs.get(columnIdx).getCellType().equals(CellType.STRING_RIGHT)) {
                        this.setStringRight(cell, strData);
                    } else {
                        this.setString(cell, strData);
                    }
                }
            } catch (final Throwable e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                this.setString(cell, strData);
            }
        }
    }

    private void setString(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellValue(this.getSecureCellText(text));
    }

    private void setStringCenter(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellStyle(this.stringCenterStyle);
        cell.setCellValue(this.getSecureCellText(text));
    }

    private void setStringRight(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellStyle(this.stringRightStyle);
        cell.setCellValue(this.getSecureCellText(text));
    }

    private void setInteger(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.NUMERIC);
        cell.setCellStyle(this.numberStyle);
        try {
            cell.setCellValue((long) Double.parseDouble(this.getSecureCellText(text)));
        } catch (final Throwable e) {
            this.logger.warn("Excel setInteger() error\n{}.", ExceptionUtils.getStackTrace(e));
            cell.setCellValue(this.getSecureCellText(text));
        }
    }

    private void setDouble(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.NUMERIC);
        cell.setCellStyle(this.doubleStyle);
        try {
            cell.setCellValue(Double.parseDouble(this.getSecureCellText(text)));
        } catch (final Throwable e) {
            this.logger.warn("Excel setDouble() error\n{}.", ExceptionUtils.getStackTrace(e));
            cell.setCellValue(this.getSecureCellText(text));
        }
    }

    private void setDate(final SXSSFCell cell, final String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.CellType.STRING);
        cell.setCellStyle(this.dateStyle);
        try {
            cell.setCellValue(this.getSecureCellText(text));
        } catch (final Throwable e) {
            this.logger.warn("Excel setDate() error\n{}.", ExceptionUtils.getStackTrace(e));
        }
    }

    private String getSecureCellText(final String text) {
        if (StringUtils.isEmpty(text) || StringUtils.equals(text, "null")) {
            return "";
        } else {
            return text;
        }
    }

    public enum CellType {
        STRING,

        STRING_CENTER,

        STRING_RIGHT,

        INTEGER,

        DOUBLE,

        DATE,

        DATE_YYYYMMDD,

        DATE_YYYYMMDDHHMMSS
    }
}
