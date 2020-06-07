package com.oracle.notebook.service.impl;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.PyInterpreter;
import com.oracle.notebook.controller.dto.ResultDto;
import com.oracle.notebook.enums.ConstantsEnum;
import com.oracle.notebook.enums.InterpretersEnum;
import com.oracle.notebook.enums.KeyWordEnum;
import com.oracle.notebook.exceptions.PythonSyntaxException;
import com.oracle.notebook.exceptions.UnexpectedPyInterpreterException;
import com.oracle.notebook.exceptions.UnknownInterpreterException;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.python.core.PyException;
import org.python.core.PyInteger;
import org.python.core.PySyntaxError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.StringWriter;

/**
 * @author zack
 *
 */

@Service
public class PythonInterpreterService implements IPythonInterpreterService {

    private static Logger logger = LoggerFactory.getLogger(PythonInterpreterService.class);

    @Override
    public ResultDto interpretPythonCode(CodeDto codeDto, HttpServletRequest request) {

        ResultDto resultDto = new ResultDto();
        String interpreterName = codeDto.getCode().split(" ")[0];
        String code = codeDto.getCode().substring(8);
        PyInterpreter interpreter = (PyInterpreter) request.getSession().getAttribute(ConstantsEnum.SESSIONID.name());

        if (interpreter == null) {
            interpreter = new PyInterpreter();
            /*
             * if interpreter object is not present in session, set interpreter in the
             * request
             */
            request.getSession().setAttribute(ConstantsEnum.SESSIONID.name(), interpreter);
        }
        request.getSession().setAttribute(ConstantsEnum.SESSIONID.name(), interpreter);

        if (interpreterName.equals(InterpretersEnum.PYTHON.getInterpreterName())) {
            try {
                StringWriter out = new StringWriter();
                interpreter.setOut(out);
                interpreter.setErr(out);

                if (code.contains(KeyWordEnum.PRINT.getKeyWord())) {
                    interpreter.exec(code);
                    resultDto.setResult(out.toString().replaceAll("\n", ""));
                } else {
                    interpreter.exec(code);
                    String[] codeInstr = code.replaceAll("\\s+", "").split("=");
                    String variableName = codeInstr[0];
                    String value = codeInstr[1];
                    interpreter.set(variableName, new PyInteger(Integer.parseInt(value)));
                    resultDto.setResult(value);
                }
            } catch (PySyntaxError ex) {
                throw new PythonSyntaxException(ex.toString());
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new PythonSyntaxException("Syntax incorrect !");
            } catch (PyException ex) {
                throw new UnexpectedPyInterpreterException(ex.toString());
            }
        } else {
            throw new UnknownInterpreterException("Invalid python interpreter");
        }

        return resultDto;
    }
}
