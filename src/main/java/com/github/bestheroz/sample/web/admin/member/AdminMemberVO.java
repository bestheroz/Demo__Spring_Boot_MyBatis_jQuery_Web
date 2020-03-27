package com.github.bestheroz.sample.web.admin.member;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Data
public class AdminMemberVO implements Serializable {
    private static final long serialVersionUID = -6673699657435061859L;
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberType;
    private Integer loginFailCnt;
    private Boolean closeTf;
    private LocalDateTime expired;
    private String updatedBy;
    private LocalDateTime updated;
}
