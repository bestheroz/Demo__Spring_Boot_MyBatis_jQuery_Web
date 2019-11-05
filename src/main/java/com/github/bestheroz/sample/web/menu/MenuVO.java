package com.github.bestheroz.sample.web.menu;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class MenuVO implements Serializable {
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

    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuId(final Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(final String menuName) {
        this.menuName = menuName;
    }

    public String getMenuNameEng() {
        return this.menuNameEng;
    }

    public void setMenuNameEng(final String menuNameEng) {
        this.menuNameEng = menuNameEng;
    }

    public Integer getParMenuId() {
        return this.parMenuId;
    }

    public void setParMenuId(final Integer parMenuId) {
        this.parMenuId = parMenuId;
    }

    public String getMenuType() {
        return this.menuType;
    }

    @SuppressWarnings("unused")
    public void setMenuType(final String menuType) {
        this.menuType = menuType;
    }

    public Boolean getUseTf() {
        return this.useTf;
    }

    public void setUseTf(final Boolean useTf) {
        this.useTf = useTf;
    }

    public Integer getPower() {
        return this.power;
    }

    public void setPower(final Integer power) {
        this.power = power;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(final Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getRemark1() {
        return this.remark1;
    }

    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

    public Integer getLvl() {
        return this.lvl;
    }

    public void setLvl(final Integer lvl) {
        this.lvl = lvl;
    }

}
