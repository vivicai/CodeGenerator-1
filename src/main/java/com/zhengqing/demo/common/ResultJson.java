package com.zhengqing.demo.common;

/**
 * 一般操作返回的结果
 */
public class ResultJson {

    private boolean success = true;
    private String msg;

    public ResultJson(){}
    public ResultJson(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
