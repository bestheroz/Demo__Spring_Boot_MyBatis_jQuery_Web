package com.github.bestheroz.sample.web.tablevo.samplemembermst;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;


@Data
public class TableSampleMemberMstVO implements Serializable {
    private static final long serialVersionUID = -1316594557905077296L;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberType;
    private Integer loginFailCnt;
    private Boolean closeTf;
    private String token;
    private LocalDateTime expired;
    private String createdBy;
    private LocalDateTime created;
    private String updatedBy;
    private LocalDateTime updated;
}
