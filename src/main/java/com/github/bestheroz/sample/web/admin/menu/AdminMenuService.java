package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstVO;
import com.github.bestheroz.standard.common.code.CodeVO;
import com.github.bestheroz.standard.common.taglibrary.MenuTag;
import com.github.bestheroz.standard.common.util.SessionUtils;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AdminMenuService {
    @Resource AdminMenuDAO adminMenuDAO;
    @Resource TableSampleMenuMstDAO tableSampleMenuMstDAO;

    public List<AdminMenuVO> getSampleMenuMstVOList(final AdminMenuVO vo) {
        return this.adminMenuDAO.getSampleMenuMstVOList(vo);
    }

    public void insertSampleMenuMst(final TableSampleMenuMstVO vo) {


        SessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.insert(vo);
    }

    public void updateSampleMenuMst(final TableSampleMenuMstVO vo) {

        SessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.update(vo, Collections.singleton("menuId"), null);
    }

    public void deleteSampleMenuMst(final TableSampleMenuMstVO vo) {
        SessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.delete(vo, Collections.singleton("menuId"));
    }

    public List<CodeVO> getPMenuValueLableVOList(final JsonObject param) {
        return this.adminMenuDAO.getPMenuValueLableVOList(param);
    }
}
