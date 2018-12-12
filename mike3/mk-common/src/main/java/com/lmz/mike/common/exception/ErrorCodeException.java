package com.lmz.mike.common.exception;

import com.lmz.mike.common.code.ICode;

public class ErrorCodeException extends RuntimeException implements ICode {

    private String code;
    private String msg;

    public ErrorCodeException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorCodeException(ICode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public ErrorCodeException(ICode code, Exception e) {
        super(e);
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getMessage() {
        return new StringBuilder().append("[").append(code).append("]-[").append(msg).append("]>>").append(super.getMessage()).toString();
    }

}
