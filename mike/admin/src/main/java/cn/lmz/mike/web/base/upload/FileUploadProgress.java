package cn.lmz.mike.web.base.upload;

import cn.lmz.mike.common.Progress;

public class FileUploadProgress extends Progress{
    private long startTime = System.currentTimeMillis();//开始时间
    private long currTime=0L;
    private boolean flag;//是否上传完成
    private String msg=null;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
