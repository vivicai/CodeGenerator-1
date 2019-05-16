package com.zhengqing.demo.util;

public class AjaxResult {

    private boolean success = true;
    private String msg;
    private int code=1;//如果成功则默认返回 1 ，失败则返回自定义的code

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //成功的封装
    public AjaxResult(String msg){
        this.msg = msg;
    }

    //如果调用这个方法,则创建的是成功时的错误信息
    public AjaxResult(String msg,int code){
        this.success = false;
        this.msg = msg;
        this.code = code;
    }
}
