package com.oracle.notebook.service.impl;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;
import com.oracle.notebook.enums.InterpretersEnum;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Arrays;

@Service
public class PythonInterpreterService implements IPythonInterpreterService {

    private static Logger logger = LoggerFactory.getLogger(PythonInterpreterService.class);

    @Override
    public ResultDto interpretPythonCode(CodeDto codeDto) throws RuntimeException {

        PythonInterpreter interpreter = new PythonInterpreter();
        StringWriter out = new StringWriter();
        interpreter.setOut(out);
        interpreter.setErr(out);
        ResultDto resultDto = new ResultDto();

        StringBuilder stringBuilder = new StringBuilder(codeDto.getCode());

        String interpreterName = stringBuilder.substring(0, 7);
        String code = stringBuilder.substring(8);

        if (interpreterName.equals(InterpretersEnum.PYTHON.getInterpreterName())) {
            logger.info("THIS IS PYTHON CODE !");
            interpreter.exec(code);
            resultDto.setResult(out.toString());
        }else {
            logger.error("THIS ISN'T PYTHON CODE !");
        }

        return resultDto;
    }

}
