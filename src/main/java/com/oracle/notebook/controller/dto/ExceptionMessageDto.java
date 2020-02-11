package com.oracle.notebook.controller.dto;

import java.io.Serializable;

public class ExceptionMessageDto implements Serializable {

    private String message;

    public ExceptionMessageDto() {
        super();
    }

    public ExceptionMessageDto(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
