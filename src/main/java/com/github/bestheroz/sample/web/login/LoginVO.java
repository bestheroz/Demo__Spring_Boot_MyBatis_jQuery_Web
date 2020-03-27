package com.github.bestheroz.sample.web.login;

import lombok.Data;
import org.joda.time.LocalDateTime;

import java.io.Serializable;


@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 8300621111265295274L;
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberType;
    private Integer loginFailCnt;
    private Boolean closeTf;
    private LocalDateTime expired;
}
