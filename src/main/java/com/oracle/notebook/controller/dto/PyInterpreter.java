package com.oracle.notebook.controller.dto;

import org.python.util.PythonInterpreter;

import java.io.Serializable;

public class PyInterpreter extends PythonInterpreter implements Serializable {

    private static final long serialVersionUID = 1L;

    public PyInterpreter() {
        super();
    }
}
