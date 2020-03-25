package com.oracle.notebook.service;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack
 */

public interface IPythonInterpreterService {

    /**
     *  interpret python code
     *
     * @param codeDto
     * @param request
     * @return
     */
    ResultDto interpretPythonCode(CodeDto codeDto, HttpServletRequest request);
}
