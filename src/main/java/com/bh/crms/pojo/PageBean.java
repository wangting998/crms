package com.bh.crms.pojo;

import java.util.List;
/**
 * 分页查询
 * @Author WWT
 * @Date 2021/1/29
 */
public class PageBean<T> {
    /**
     * 1、当前页码   pageCode
     * 2、总记录数   totalRecord
     * 3、每页记录数 pageSize
     * 4、url
     * 5、当前页记录  beanList
     * 6、总页数   totalPage
     */
    private int pc; //
    private int tr;
    private int ps;
    //    private int tp;
    private String url;
    private List<T> beanList;

    public PageBean(int pc, int tr, int ps) {
        this.pc = pc;
        this.tr = tr;
        this.ps = ps;
    }

    //返回当前页首行下标
    public int getIndex() {
        return (pc - 1) * ps;
    }

    //返回总页数
    public int getTp() {
        int tp = tr / ps;
        if (tr % ps != 0) {
            tp++;
        }
        return tp;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }
}