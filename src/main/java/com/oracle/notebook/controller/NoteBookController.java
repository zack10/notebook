package com.oracle.notebook.controller;

import com.oracle.notebook.controller.dto.CodeDto;
import com.oracle.notebook.controller.dto.ResultDto;
import com.oracle.notebook.enums.InterpretersEnum;
import com.oracle.notebook.service.IPythonInterpreterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notebook")
public class NoteBookController {

    private static Logger logger = LoggerFactory.getLogger(NoteBookController.class);
    private IPythonInterpreterService pythonInterpreterService;

    @Autowired
    NoteBookController(IPythonInterpreterService pythonInterpreterService) {
        this.pythonInterpreterService = pythonInterpreterService;
    }

    @PostMapping(value = "/execute", headers = "Accept=application/json")
    public ResponseEntity<ResultDto> execute(@RequestBody CodeDto codeDto) {
        pythonInterpreterService.interpretPythonCode(codeDto);
        return new ResponseEntity<>(pythonInterpreterService.interpretPythonCode(codeDto), HttpStatus.OK);
    }

}
