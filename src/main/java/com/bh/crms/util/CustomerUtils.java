package com.bh.crms.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * string转化日期对象
     * @return
     */
    public static Date StringToDate(String s) throws ParseException {
        return  new SimpleDateFormat("yyyy-MM-dd").parse(s);
    }

    /**
     * 将日期对象转化为string类型
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
