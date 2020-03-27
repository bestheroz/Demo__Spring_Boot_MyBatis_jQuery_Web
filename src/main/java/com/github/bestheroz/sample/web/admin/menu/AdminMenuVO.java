package com.github.bestheroz.sample.web.admin.menu;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;


@Data
public class AdminMenuVO implements Serializable {
    private static final long serialVersionUID = -3788556701231511980L;
    private Integer menuId;
    private String menuName;
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

}
