package com.casi.commons.util;

/**
 * User: hadoop
 * Date: 12-2-8
 * Time: 下午12:48
 */
public class Page {
    protected int start=0;
    protected int pageSize=1000;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
