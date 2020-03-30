package com.github.bestheroz.standard.common.file.excel;

import com.github.bestheroz.standard.common.code.CodeVO;
import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import lombok.Data;

import java.util.List;

@Data
public class ExcelVO {
    private String title;
    private String dbColName;
    private Double charByte = 1.0D; // 셀넓이를 추가로 주기 위함..(한글과 영어 넓이 다름.)
    private AbstractExcelXView.CellType cellType;
    private List<CodeVO> codeList;
}
