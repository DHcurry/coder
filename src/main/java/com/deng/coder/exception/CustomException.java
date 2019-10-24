package com.deng.coder.exception;

public class CustomException extends RuntimeException {
    private String message;

    public CustomException(ICustomErrorcode error){
        this.message = error.getMessage();
    }

    @Override
    public String getMessage(){
        return message;
    }
}
