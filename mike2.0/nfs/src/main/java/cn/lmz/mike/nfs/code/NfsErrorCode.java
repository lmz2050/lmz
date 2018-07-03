package cn.lmz.mike.nfs.code;

import cn.lmz.mike.common.code.ICode;

public enum NfsErrorCode implements ICode {

    FILE_IS_EMPTY("97001", "file_is_empty");

    private String code;
    private String msg;

    NfsErrorCode() {}
    NfsErrorCode(String code, String description) {
        this.code = code;
        this.msg = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
