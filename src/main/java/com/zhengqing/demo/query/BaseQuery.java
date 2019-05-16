package com.zhengqing.demo.query;

public class BaseQuery {

    private String keyword;//关键字
    private int page = 1;//页数
    private int limit = 10;//每页的条目数

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
