package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.file.excel.ExcelService;
import com.github.bestheroz.standard.common.file.excel.ExcelVO;
import com.github.bestheroz.standard.common.file.excel.HugeExcelService;
import com.github.bestheroz.standard.common.file.pdf.PdfService;
import com.github.bestheroz.standard.common.file.pdf.PdfVO;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelService;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelVO;
import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import com.github.bestheroz.standard.context.abstractview.AbstractPdfboxView;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminMenuController {
    @Resource AdminMenuService adminMenuService;
    @Resource ValueLabelService valueLabelService;

    @GetMapping(value = "/sample/admin/menu/adminMenu.view")
    public String home() {
        return "/sample/admin/menu/AdminMenu";
    }

    @PostMapping(value = "/sample/admin/menu/getSampleMenuMstVOList.json")
    @ResponseBody
    public List<AdminMenuVO> getSampleMenuMstVOList(final AdminMenuVO vo) throws CommonException {
        return this.adminMenuService.getSampleMenuMstVOList(vo);
    }

    @PostMapping(value = "/sample/admin/menu/insertSampleMenuMst.json")
    @ResponseBody
    public JsonObject insertSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.insertSampleMenuMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/menu/updateSampleMenuMst.json")
    @ResponseBody
    public JsonObject updateSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.updateSampleMenuMst(vo, MySessionUtils.getLoginVO());
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/menu/deleteSampleMenuMst.json")
    @ResponseBody
    public JsonObject deleteSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        this.adminMenuService.deleteSampleMenuMst(vo);
        return CommonException.SUCCESS_NORMAL.getJsonObject();
    }

    @PostMapping(value = "/sample/admin/menu/getPMenuValueLableVOList.json")
    @ResponseBody
    public List<ValueLabelVO> getPMenuValueLableVOList(final AdminMenuVO vo) throws CommonException {
        return this.adminMenuService.getPMenuValueLableVOList(null);
    }

    @PostMapping(value = "/sample/admin/menu/adminMenu.xlsx")
    public String getExcel(final Model model, final AdminMenuVO vo) throws CommonException {
        model.addAttribute(AbstractExcelXView.FILE_NAME, "메뉴리스트");

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, "메뉴ID", "menuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "메뉴명", "menuName", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "메뉴구분", "menuType", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVOList("MENU_TYPE"));
        AbstractExcelXView.addHeader(excelVOList, "부모메뉴ID", "parMenuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "사용여부", "useTf", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVOList("USE_TF"));
        AbstractExcelXView.addHeader(excelVOList, "권한", "power", ExcelService.CellType.STRING_RIGHT, this.valueLabelService.getValueLabelVOList("MEMBER_TYPE"));
        AbstractExcelXView.addHeader(excelVOList, "(같은그룹내)출력순서", "displayOrder", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, "URL", "url", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "비고", "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "작업일시", "updated", ExcelService.CellType.DATE);
        AbstractExcelXView.addHeader(excelVOList, "작업자", "updatedBy", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        model.addAttribute(AbstractExcelXView.LIST_DATA, this.adminMenuService.getSampleMenuMstVOList(vo));

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return ExcelService.VIEW_NAME;
    }

    @PostMapping(value = "/sample/admin/menu/adminMenuHugeExcel.xlsx")
    public String getHugeExcel(final Model model, final AdminMenuVO vo) {
        model.addAttribute(AbstractExcelXView.FILE_NAME, "메뉴리스트");

        final List<ExcelVO> excelVOList = new ArrayList<>();
        AbstractExcelXView.addHeader(excelVOList, "메뉴ID", "menuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "메뉴명", "menuName", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "메뉴구분", "menuType", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVOList("MENU_TYPE"));
        AbstractExcelXView.addHeader(excelVOList, "부모메뉴ID", "parMenuId", ExcelService.CellType.STRING_CENTER);
        AbstractExcelXView.addHeader(excelVOList, "사용여부", "useTf", ExcelService.CellType.STRING_CENTER, this.valueLabelService.getValueLabelVOList("USE_TF"));
        AbstractExcelXView.addHeader(excelVOList, "권한", "power", ExcelService.CellType.STRING_RIGHT, this.valueLabelService.getValueLabelVOList("MEMBER_TYPE"));
        AbstractExcelXView.addHeader(excelVOList, "(같은그룹내)출력순서", "displayOrder", ExcelService.CellType.INTEGER);
        AbstractExcelXView.addHeader(excelVOList, "URL", "url", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "비고", "remark1", ExcelService.CellType.STRING);
        AbstractExcelXView.addHeader(excelVOList, "작업일시", "updated", ExcelService.CellType.DATE);
        AbstractExcelXView.addHeader(excelVOList, "작업자", "updatedBy", ExcelService.CellType.STRING);
        model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);

        model.addAttribute(AbstractExcelXView.SQL, "SELECT SMM.MENU_ID , SMM.MENU_NAME , SMM.MENU_TYPE , SMM.PAR_MENU_ID , SMM.USE_TF , SMM.POWER ," +
                " SMM.DISPLAY_ORDER , SMM.URL , SMM.REMARK1 , SMM.UPDATED , SMM.UPDATED_BY FROM SAMPLE_MENU_MST SMM WHERE 1=1 ORDER BY SMM.UPDATED DESC");

        // model.addAttribute(ExcelService.PASSWORD, "1234");

        return HugeExcelService.VIEW_NAME;
    }

    @PostMapping(value = "/sample/admin/menu/adminMenu.pdf")
    public String getPdf(final Model model, final AdminMenuVO vo) throws CommonException {
        model.addAttribute(AbstractPdfboxView.FILE_NAME, "메뉴리스트");

        final List<PdfVO> pdfVOList = new ArrayList<>();
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴ID", "menuId", AbstractPdfboxView.CellType.STRING_CENTER, 10f);
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴명", "menuName", AbstractPdfboxView.CellType.STRING, 20f);
        AbstractPdfboxView.addPdfDataType(pdfVOList, "메뉴구분", "menuType", AbstractPdfboxView.CellType.STRING_CENTER, 10f,
                this.valueLabelService.getValueLabelVoListToJsonObject("MENU_TYPE"));
        AbstractPdfboxView.addPdfDataType(pdfVOList, "URL", "url", AbstractPdfboxView.CellType.STRING, 40f);
        model.addAttribute(AbstractPdfboxView.PDF_VOS, pdfVOList);

        model.addAttribute(AbstractPdfboxView.LIST_DATA, this.adminMenuService.getSampleMenuMstVOList(vo));

        return PdfService.VIEW_NAME;
    }
}
