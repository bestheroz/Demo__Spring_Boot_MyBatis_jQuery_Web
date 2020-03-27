package com.github.bestheroz.sample.web.tablevo.samplecodemst;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Data
public class TableSampleCodeMstVO implements Serializable {
    private static final long serialVersionUID = -721078445838855067L;
    private String groupCode;
    private String groupCodeName;
    private String remark1;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
}
