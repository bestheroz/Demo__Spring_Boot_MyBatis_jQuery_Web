package com.github.bestheroz.standard.common.file.excel;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelService extends AbstractExcelXView {
    public static final String VIEW_NAME = "excelView";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void buildExcelDocument(final Map<String, Object> model, final SXSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked") final List<ExcelVO> excelVOs = (List<ExcelVO>) model.get(AbstractExcelXView.EXCEL_VOS);
        final JsonArray listData = MyMapperUtils.writeObjectAsJsonArray(model.get(AbstractExcelXView.LIST_DATA));
        final String fileName = MyFileUtils.getEncodedFileName(request, (String) model.get(AbstractExcelXView.FILE_NAME));

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "_" + MyDateUtils.getStringNow(MyDateUtils.YYYYMMDDHHMMSS) + AbstractExcelXView.EXTENSION + ";");

        final SXSSFSheet sheet = workbook.createSheet("report");
        sheet.setRandomAccessWindowSize(100);

        this.createColumnLabel(sheet, excelVOs);

        this.addRowData(sheet, excelVOs, listData);
        this.autoSizeColumn(sheet, excelVOs);
    }

    @SuppressWarnings("DuplicatedCode")
    private void createColumnLabel(final SXSSFSheet sheet, final List<ExcelVO> excelVOs) {
        final SXSSFRow row0 = sheet.createRow(0);
        final CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, excelVOs.size() - 1);
        sheet.addMergedRegion(mergedRegion);
        final SXSSFCell cell10 = row0.createCell(0);
        cell10.setCellValue("This report was generated at " + MyDateUtils.getStringNow(MyDateUtils.YYYY_MM_DD_HH_MM_SS));

        final XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        final XSSFFont font = (XSSFFont) sheet.getWorkbook().createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        final SXSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < excelVOs.size(); i++) {
            final SXSSFCell cell = row2.createCell(i);
            cell.setCellValue(excelVOs.get(i).getTitle());
            cell.setCellStyle(cellStyle);
        }
    }

    private void addRowData(final SXSSFSheet sheet, final List<ExcelVO> excelVOs, final JsonArray listData) {
        for (int i = 0; i < listData.size(); i++) {
            if (i != 0 && i % 100 == 0) {
                this.logger.debug("[Excel]{} writed {} rows", sheet.getSheetName(), i + 1);
            }
            final SXSSFRow row = sheet.createRow(3 + i);
            final JsonObject jo = listData.get(i).getAsJsonObject();
            for (int j = 0; j < excelVOs.size(); j++) {
                final JsonElement jsonElement = jo.get(excelVOs.get(j).getDbColName());
                if (jsonElement != null && !jsonElement.isJsonNull() && StringUtils.isNotEmpty(jsonElement.getAsString())) {
                    this.writeColumnData(excelVOs, j, row.createCell(j), jsonElement.getAsString());
                }
            }
        }
    }
}
