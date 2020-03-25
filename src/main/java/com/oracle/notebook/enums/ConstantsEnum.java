package com.oracle.notebook.enums;

/**
 * @author zack
 *
 */

public enum ConstantsEnum {

    SESSIONID("JSESSIONID");

    private final String sessionsId;

    ConstantsEnum(String sessionsId) {
        this.sessionsId = sessionsId;
    }

    public String getSessionsId() {
        return sessionsId;
    }
}
