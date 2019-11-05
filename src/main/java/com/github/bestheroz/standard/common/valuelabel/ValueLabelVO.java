package com.github.bestheroz.standard.common.valuelabel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValueLabelVO implements Serializable {
    private String label;
    private String value;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
