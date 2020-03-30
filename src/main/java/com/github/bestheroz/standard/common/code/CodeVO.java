package com.github.bestheroz.standard.common.code;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeVO implements Serializable {
    private static final long serialVersionUID = 272726757907169621L;
    private String label;
    private String value;
}
