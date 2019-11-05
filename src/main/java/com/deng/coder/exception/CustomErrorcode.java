package com.deng.coder.exception;

public enum CustomErrorcode implements ICustomErrorcode{
    QUESTION_NOT_FOUND("问题不存在"),

    REPEAT_NOT_FOUND("回复不存在");

    CustomErrorcode(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
