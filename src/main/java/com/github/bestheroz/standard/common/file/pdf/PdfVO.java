package com.github.bestheroz.standard.common.file.pdf;

import com.github.bestheroz.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonElement;

public class PdfVO {
    private String title;
    private String dbColName;
    private float width;
    private AbstractPdfboxView.CellType cellType;
    private JsonElement codeObject;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDbColName() {
        return this.dbColName;
    }

    public void setDbColName(final String dbColName) {
        this.dbColName = dbColName;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(final float width) {
        this.width = width;
    }

    public AbstractPdfboxView.CellType getCellType() {
        return this.cellType;
    }

    public void setCellType(final AbstractPdfboxView.CellType cellType) {
        this.cellType = cellType;
    }

    public JsonElement getCodeObject() {
        return this.codeObject;
    }

    public void setCodeObject(final JsonElement codeObject) {
        this.codeObject = codeObject;
    }

}
