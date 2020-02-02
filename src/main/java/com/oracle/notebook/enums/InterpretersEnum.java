package com.oracle.notebook.enums;

public enum InterpretersEnum {

    PYTHON("%python");

    private final String interpreterName;

    InterpretersEnum(String interpreterName) {
        this.interpreterName = interpreterName;
    }

    public String getInterpreterName() {
        return interpreterName;
    }
}
