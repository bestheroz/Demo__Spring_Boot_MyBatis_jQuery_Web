package com.github.bestheroz.sample.web.menu;

import com.google.gson.JsonObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDAO {
    List<MenuVO> getMenuVOList(final JsonObject param);
}
