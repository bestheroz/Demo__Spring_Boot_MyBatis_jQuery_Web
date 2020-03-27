package com.github.bestheroz.sample.web.menu;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Data
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 4754045913029445652L;
    private Integer menuId;
    private String menuName;
    private String menuNameEng;
    private Integer parMenuId;
    private String menuType;
    private Boolean useTf;
    private Integer power;
    private Integer displayOrder;
    private String url;
    private String remark1;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
    private Integer lvl;
}
