package com.github.bestheroz.standard.common.file.excel;

import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import com.google.gson.JsonElement;

public class ExcelVO {
    private String title;
    private String dbColName;
    private Double charByte = 1.0D; // 셀넓이를 추가로 주기 위함..(한글과 영어 넓이 다름.)
    private AbstractExcelXView.CellType cellType;
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

    public Double getCharByte() {
        return this.charByte;
    }

    public void setCharByte(final Double charByte) {
        this.charByte = charByte;
    }

    public AbstractExcelXView.CellType getCellType() {
        return this.cellType;
    }

    public void setCellType(final AbstractExcelXView.CellType cellType) {
        this.cellType = cellType;
    }

    public JsonElement getCodeObject() {
        return this.codeObject;
    }

    public void setCodeObject(final JsonElement codeObject) {
        this.codeObject = codeObject;
    }

}