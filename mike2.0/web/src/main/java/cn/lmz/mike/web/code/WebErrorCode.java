package cn.lmz.mike.web.code;

import cn.lmz.mike.common.code.ICode;

public enum WebErrorCode implements ICode{

    SUCCESS("0", "成功"),
    SYSTEM_ERROR("4001", "系统异常"),
    BUSINESS_ERROR("6001", "业务异常"),
    VALIDATE_ERROR("8001", "验证异常");

    private String code;
    private String msg;

    WebErrorCode(){}
    WebErrorCode(String code, String description) {
        this.code = code;
        this.msg = description;
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
}
