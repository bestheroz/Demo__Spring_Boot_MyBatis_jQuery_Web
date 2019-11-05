package com.github.bestheroz.sample.web.admin.valuelabel;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AdminValueLabelVO implements Serializable {
    private String groupCode;
    private String code;
    private String codeName;
    private String codenmE;
    private String groupCodeName;
    private String groupCodenmE;
    private Boolean useTf;
    private Integer displayOrder;
    private String remark1;
    private String updatedBy;
    private LocalDateTime updated;

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(final String groupCode) {
        this.groupCode = groupCode;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getCodenmK() {
        return this.codeName;
    }

    public void setCodenmK(final String codeName) {
        this.codeName = codeName;
    }

    public String getCodenmE() {
        return this.codenmE;
    }

    public void setCodenmE(final String codenmE) {
        this.codenmE = codenmE;
    }

    public String getGroupCodeName() {
        return this.groupCodeName;
    }

    public void setGroupCodeName(final String groupCodeName) {
        this.groupCodeName = groupCodeName;
    }

    public String getGroupCodenmE() {
        return this.groupCodenmE;
    }

    public void setGroupCodenmE(final String groupCodenmE) {
        this.groupCodenmE = groupCodenmE;
    }

    public Boolean getUseTf() {
        return this.useTf;
    }

    public void setUseTf(final Boolean useTf) {
        this.useTf = useTf;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(final Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getRemark1() {
        return this.remark1;
    }

    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
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

}
