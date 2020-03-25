package com.oracle.notebook.controller.dto;

import java.io.Serializable;

/**
 * @author zack
 *
 */

public class ExceptionMessageDto implements Serializable {

    private String message;

    public ExceptionMessageDto() {
        super();
    }

    /**
     *
     * @param message
     */
    public ExceptionMessageDto(String message) {
        super();
        this.message = message;
    }

    /**
     * get @param message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * set @param message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
