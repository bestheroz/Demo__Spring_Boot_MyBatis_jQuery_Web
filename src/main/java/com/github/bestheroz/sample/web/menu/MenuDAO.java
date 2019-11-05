package com.github.bestheroz.sample.web.menu;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDAO {
    List<MenuVO> getMenuVOList(final JsonObject param) throws CommonException;
}
