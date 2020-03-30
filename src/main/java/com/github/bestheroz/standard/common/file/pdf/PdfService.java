package com.github.bestheroz.standard.common.file.pdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.github.bestheroz.standard.common.util.DateUtils;
import com.github.bestheroz.standard.common.util.FileUtils;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import com.github.bestheroz.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This view class generates a PDF document 'on the fly' based on the data contained in the model.
 *
 * @author www.codejava.net
 */
@Slf4j
public class PdfService extends AbstractPdfboxView {
    public static final String VIEW_NAME = "pdfView";
    private static final String FONT_PATH = "/font/MALGUNBD.TTF";
    private static final Double PAGE_MARGIN = 20.0;

    @Override
    protected void buildPdfDocument(final Map<String, Object> model, final PDDocument document, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // get data model which is passed by the Spring container
        @SuppressWarnings("unchecked") final List<PdfVO> pdfVOs = (List<PdfVO>) model.get(AbstractPdfboxView.PDF_VOS);
        final JsonArray listData = MapperUtils.toJsonArray(model.get(AbstractPdfboxView.LIST_DATA));
        final String fileName = FileUtils.getEncodedFileName(request, (String) model.get(AbstractPdfboxView.FILE_NAME));

        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "_" + DateUtils.getStringNow(DateUtils.YYYYMMDDHHMMSS) + AbstractPdfboxView.EXTENSION + ";");

//        final float[] widths = new float[pdfVOs.size()];
//        for (int i = 0; i < pdfVOs.size(); i++) {
//            widths[i] = pdfVOs.get(i).getWidth();
//        }

        final PDPage page = new PDPage();
        page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        document.addPage(page);

        super.font = PDType0Font.load(document, new ClassPathResource(FONT_PATH).getFile());

        final double tableWidth = page.getMediaBox().getWidth() - 2 * PAGE_MARGIN;
        final double yStartNewPage = page.getMediaBox().getHeight() - 2 * PAGE_MARGIN;
        final double bottomMargin = 0.0;

        final BaseTable table =
                new BaseTable((float) yStartNewPage, (float) yStartNewPage, (float) bottomMargin, (float) tableWidth, PAGE_MARGIN.floatValue(), document, page, true,
                        true);
        this.createColumnLabel(document, table, pdfVOs);
        this.addRowData(document, table, pdfVOs, listData);

    }

    private void createColumnLabel(final PDDocument document, final BaseTable table, final List<PdfVO> pdfVOs) {
        // Create Header row
        final Row<PDPage> headerRow = table.createRow(15F);
        for (final PdfVO pdfVO : pdfVOs) {
            final Cell<PDPage> cell = headerRow.createCell(pdfVO.getWidth(), pdfVO.getTitle());
            cell.setFont(super.font);
            cell.setFillColor(Color.LIGHT_GRAY);
        }
        table.addHeaderRow(headerRow);
    }

    private void addRowData(final PDDocument document, final BaseTable table, final List<PdfVO> pdfVOs, final JsonArray listData) throws IOException {
        for (int i = 0; i < listData.size(); i++) {
            if (i != 0 && i % 200 == 0) {
                log.debug("[PDF] write {} rows", i + 1);
            }

            final Row<PDPage> row = table.createRow(10F);
            final JsonObject rowData = listData.get(i).getAsJsonObject();
            for (int j = 0; j < pdfVOs.size(); j++) {
                final JsonElement jsonElement = rowData.get(pdfVOs.get(j).getDbColName());
                String strData = "";
                if (NullUtils.isNotEmpty(jsonElement)) {
                    strData = jsonElement.getAsString();
                }
                this.writeColumnData(pdfVOs, row, j, strData);
            }
        }
        table.draw();
    }

}
