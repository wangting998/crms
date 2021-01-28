package com.bh.crms.util;
import java.util.UUID;

/**
 * 工具类----UUTD
 * @Author WWT
 * @Date 2021/1/27
 */
public class CustomerUtils {
    /**
     * 返回一个不重复的字符串
     * UUID获取cid
     * @return
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }
}
