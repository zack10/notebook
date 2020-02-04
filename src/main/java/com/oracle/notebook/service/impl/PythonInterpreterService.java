package com.oracle.notebook.service.impl;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;
import com.oracle.notebook.enums.InterpretersEnum;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Arrays;

@Service
public class PythonInterpreterService implements IPythonInterpreterService {

    private static Logger logger = LoggerFactory.getLogger(PythonInterpreterService.class);

    static PythonInterpreter interpreter = new PythonInterpreter();

    @Override
    public ResultDto interpretPythonCode(CodeDto codeDto) throws RuntimeException {

        ResultDto resultDto = new ResultDto();

        StringBuilder stringBuilder = new StringBuilder(codeDto.getCode());

        String interpreterName = codeDto.getCode().split(" ")[0];
        String code = stringBuilder.substring(8);

        if (interpreterName.equals(InterpretersEnum.PYTHON.getInterpreterName())) {
            logger.info("THIS IS PYTHON CODE !");

            StringWriter out = new StringWriter();

            interpreter.setOut(out);
            interpreter.setErr(out);

            if (code.contains("print")) {
                interpreter.exec(code);
                resultDto.setResult(out.toString());
            }else {
                String[] codeInstr = code.split("=");
                interpreter.set(codeInstr[0], new PyInteger(Integer.parseInt(codeInstr[1])));
            }

        }else {
            logger.error("THIS ISN'T PYTHON CODE !");
        }

        return resultDto;
    }

}
