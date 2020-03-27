package com.github.bestheroz.sample.web.admin.valuelabel;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;


@Data
public class AdminValueLabelVO implements Serializable {
    private static final long serialVersionUID = -2317659184277631437L;
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
}
