package com.oracle.notebook.enums;

/**
 * @author zack
 *
 */

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
