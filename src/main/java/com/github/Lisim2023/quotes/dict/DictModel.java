package com.github.Lisim2023.quotes.dict;

import java.io.Serializable;


/**
 * 字典数据封装类
 */
public class DictModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String value;
    private String label;

    public DictModel() {
    }

    public DictModel(String code, String value, String label) {
        this.code = code;
        this.value = value;
        this.label = label;
    }





    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
