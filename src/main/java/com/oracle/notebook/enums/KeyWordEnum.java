package com.oracle.notebook.enums;

public enum KeyWordEnum {

    PRINT("print");

    private final String keyWord;

    KeyWordEnum(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
