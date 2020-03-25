package com.oracle.notebook.controller.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zack
 *
 */

public class CodeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String code;

    public CodeDto() {
        super();
    }

    /**
     *
     * @param code
     */
    public CodeDto(String code) {
        super();
        this.code = code;
    }

    /**
     * get @param code
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * set @param code
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
