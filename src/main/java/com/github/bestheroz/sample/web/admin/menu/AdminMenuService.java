package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemenumst.TableSampleMenuMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.taglibrary.MenuTag;
import com.github.bestheroz.standard.common.util.MySessionUtils;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelVO;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AdminMenuService {
    @Resource AdminMenuDAO adminMenuDAO;
    @Resource TableSampleMenuMstDAO tableSampleMenuMstDAO;

    public List<AdminMenuVO> getSampleMenuMstVOList(final AdminMenuVO vo) throws CommonException {
        return this.adminMenuDAO.getSampleMenuMstVOList(vo);
    }

    public void insertSampleMenuMst(final TableSampleMenuMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setCreatedBy(loginVO.getMemberId());
        vo.setUpdatedBy(loginVO.getMemberId());
        MySessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.insert(vo);
    }

    public void updateSampleMenuMst(final TableSampleMenuMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setUpdatedBy(loginVO.getMemberId());
        MySessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.update(vo, Collections.singleton("menuId"), null);
    }

    public void deleteSampleMenuMst(final TableSampleMenuMstVO vo) throws CommonException {
        MySessionUtils.removeAttribute(MenuTag.MENU_TAG);
        this.tableSampleMenuMstDAO.delete(vo, Collections.singleton("menuId"));
    }

    public List<ValueLabelVO> getPMenuValueLableVOList(final JsonObject param) throws CommonException {
        return this.adminMenuDAO.getPMenuValueLableVOList(param);
    }
}
