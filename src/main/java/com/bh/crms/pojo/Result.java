package com.bh.crms.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WWT
 * @Date 2021/3/2
 */
public class Result {
    //成员变量
    private  Integer code;
    private String message;
    private List arrayList;

    public Result(Integer code, String message, List arrayList) {
        this.code = code;
        this.message = message;
        this.arrayList = arrayList;
    }

    //成员方法
}
