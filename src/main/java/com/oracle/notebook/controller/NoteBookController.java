package com.oracle.notebook.controller;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zack
 *
 */

@RestController
@RequestMapping("/notebook")
public class NoteBookController {

    private static Logger logger = LoggerFactory.getLogger(NoteBookController.class);
    private IPythonInterpreterService pythonInterpreterService;

    @Autowired
    NoteBookController(IPythonInterpreterService pythonInterpreterService) {
        this.pythonInterpreterService = pythonInterpreterService;
    }

    /**
     * POST web service to execute python code
     * @param codeDto
     * @param request
     * @return
     */
    @PostMapping(value = "/execute", headers = "Accept=application/json")
    public ResponseEntity<ResultDto> execute(@Valid @RequestBody CodeDto codeDto, HttpServletRequest request) {
        ResultDto resultDto = pythonInterpreterService.interpretPythonCode(codeDto, request);
        return ResponseEntity.ok(resultDto);
    }
}
