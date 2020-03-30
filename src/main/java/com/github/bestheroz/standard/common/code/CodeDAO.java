package com.github.bestheroz.standard.common.code;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CodeDAO {
    @Select(value = "SELECT SCD.CODE, SCD.CODE_NAME FROM SAMPLE_CODE_DET SCD WHERE SCD.GROUP_CODE = #{groupCode, jdbcType=VARCHAR} ORDER BY SCD.DISPLAY_ORDER ASC")
    @Results(value = {@Result(column = "CODE", property = "value"), @Result(column = "CODE_NAME", property = "label")})
    List<CodeVO> getCodeVOList(final String groupCode);
}
