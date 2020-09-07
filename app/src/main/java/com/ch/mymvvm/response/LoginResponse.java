package com.ch.mymvvm.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.ch.mymvvm.bean.Item;
import com.ch.mymvvm.data.http.BaseResponse;

import java.util.List;


public class LoginResponse<T> extends BaseResponse {

    /**
     * curPage : 1
     * offset : 0
     * over : false
     * pageCount : 458
     * size : 20
     * total : 9143
     */

    @JSONField(name = "datas")
    List<T> datas;
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
