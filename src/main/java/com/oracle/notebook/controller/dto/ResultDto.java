package com.oracle.notebook.controller.dto;

import java.io.Serializable;

/**
 * @author zack
 *
 */

public class ResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String result;

    public ResultDto() {
        super();
    }

    /**
     *
     * @param result
     */
    public ResultDto(String result) {
        super();
        this.result = result;
    }

    /**
     * get @param result
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     * set @param result
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }
}
