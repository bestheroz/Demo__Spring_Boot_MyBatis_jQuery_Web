package com.github.bestheroz.standard.common.valuelabel;

import lombok.Data;

import java.io.Serializable;


@Data
public class ValueLabelVO implements Serializable {
    private static final long serialVersionUID = 3462503099318090661L;
    private String label;
    private String value;
}
