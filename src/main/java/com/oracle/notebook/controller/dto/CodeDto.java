package com.oracle.notebook.controller.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CodeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String code;

    public CodeDto() {
        super();
    }

    public CodeDto(String code) {
        super();
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
