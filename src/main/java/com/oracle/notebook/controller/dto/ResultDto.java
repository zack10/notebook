package com.oracle.notebook.controller.dto;

import java.io.Serializable;

public class ResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String result;

    public ResultDto() {
        super();
    }

    public ResultDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
