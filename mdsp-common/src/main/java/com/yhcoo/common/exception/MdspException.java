package com.yhcoo.common.exception;

public class MdspException extends RuntimeException{

    private Integer code;
    private String errorMessage;

    public MdspException() {
    }

    public MdspException(String message) {
        super(message);
    }

    public MdspException(MessageExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }
}
