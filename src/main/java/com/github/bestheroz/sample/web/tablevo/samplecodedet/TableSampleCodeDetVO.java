package com.github.bestheroz.sample.web.tablevo.samplecodedet;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Data
public class TableSampleCodeDetVO implements Serializable {
    private static final long serialVersionUID = -6480424590689061305L;
    private String groupCode;
    private String code;
    private String codeName;
    private Boolean useTf;
    private Integer displayOrder;
    private String remark1;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
}
