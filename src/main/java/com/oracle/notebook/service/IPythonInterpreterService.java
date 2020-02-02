package com.oracle.notebook.service;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;

public interface IPythonInterpreterService {
    ResultDto interpretPythonCode(CodeDto codeDto) throws RuntimeException;
}
