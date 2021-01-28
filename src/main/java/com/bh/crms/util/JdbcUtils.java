package com.bh.crms.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 与数据库连接生成----工具类
 * 使用本类的方法，必须提供c3p0-config.xml文件
 * @Author WWT
 * @Date 2021/1/27
 */
public class JdbcUtils {
    /**
     * 数据源，连接池
     * 读取的是C3P0-config默认配置创建数据库连接池对象
     */
    private static DataSource dataSource = new ComboPooledDataSource("c3p0-config.xml");
    /**
     * 获取数据源/数据库连接池对象
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 从连接池中获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
