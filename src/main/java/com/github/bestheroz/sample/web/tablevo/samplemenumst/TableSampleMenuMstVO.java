package com.github.bestheroz.sample.web.tablevo.samplemenumst;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;


@Data
public class TableSampleMenuMstVO implements Serializable {
    private static final long serialVersionUID = 6133619723469320593L;
    private Integer menuId;
    private String menuName;
    private String menuType;
    private Integer parMenuId;
    private Boolean useTf;
    private Integer power;
    private Integer displayOrder;
    private String url;
    private String remark1;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
}
