package com.github.bestheroz.standard.common.file.pdf;

import com.github.bestheroz.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonElement;
import lombok.Data;

@Data
public class PdfVO {
    private String title;
    private String dbColName;
    private float width;
    private AbstractPdfboxView.CellType cellType;
    private JsonElement codeObject;
}
