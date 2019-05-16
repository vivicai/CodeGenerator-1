package com.zhengqing.test;

public class FieldVo {

    private String title;

    private String filed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public FieldVo() {
    }

    public FieldVo(String title, String filed) {
        this.title = title;
        this.filed = filed;
    }
}
