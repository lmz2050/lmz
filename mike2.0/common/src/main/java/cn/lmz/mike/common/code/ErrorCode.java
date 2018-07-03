package cn.lmz.mike.common.code;

public enum ErrorCode implements ICode{

    SUCCESS("0", "成功"),
    SYSTEM_ERROR("40001", "系统异常"),
    BUSINESS_ERROR("60001", "业务异常"),
    DATA_ERROR("80001", "数据异常"),
    VALIDATE_ERROR("90001", "验证异常");


    private String code;
    private String msg;

    ErrorCode(){}
    ErrorCode(String code, String description) {
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
