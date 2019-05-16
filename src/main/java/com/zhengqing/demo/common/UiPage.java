package com.zhengqing.demo.common;

import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 传给前台的分页对象
 * 注意：为什么要写这个？？  --> 前台和后天分页时传递的参数名不同 因此需要转换
 */
public class UiPage<T> {

    private String code;
    private String msg;
    private Long count; //总条数
    private List<T> data = new ArrayList(); //装前台当前页的数据

    public UiPage(Page page) {
//        this.data = page.getContent();
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
