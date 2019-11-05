package com.github.bestheroz.sample.web.admin.menu;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.valuelabel.ValueLabelVO;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMenuDAO {
    List<AdminMenuVO> getSampleMenuMstVOList(final AdminMenuVO vo) throws CommonException;

    @Select("SELECT SMM.MENU_ID, SMM.MENU_NAME FROM SAMPLE_MENU_MST SMM WHERE SMM.MENU_TYPE = 'G' ORDER BY SMM.MENU_ID ASC")
    @Results(value = {@Result(column = "MENU_ID", property = "value"), @Result(column = "MENU_NAME", property = "label")})
    List<ValueLabelVO> getPMenuValueLableVOList(final JsonObject param) throws CommonException;
}
