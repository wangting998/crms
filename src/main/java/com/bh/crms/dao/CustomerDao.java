package com.bh.crms.dao;

import com.bh.crms.pojo.Customer;
import com.bh.crms.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @Author WWT
 * @Date 2021/1/27
 */
public class CustomerDao {
    /**
     * 获取数据源
     * QreryRunner类(org.apache.commons.dbutils.QueryRunner) 是Dbutils的核心类之一
     * 它简化了SQL查询，并与ResultSetHandler协同工作将使编码量大为减少
     */
    private static QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

    /**
     * 查询所有
     *
     * @param
     */
    public static List findAll() {
        /**
         * 1.给出sql
         * 2.找参数
         */
        String sql = "select * from tb_customer";
        List<Customer> list = null;
        try {
            //BeanListHandler：多行处理器！把结果集转换成 List<Bean>；
            //query()：执行 select 语句
            list= qr.query(sql, new BeanListHandler<>(Customer.class));
        } catch (SQLException throwables) {
            System.out.println("查询失败");
        }
        return list;
    }



    /**
     * 添加数据/客户
     * @param c
     */
    public void addCustomer(Customer c) {
        /**
         * 1.给出sql
         * 2.找参数
         */
        //1.给出sql
        String sql = "insert into tb_customer values (?,?,?,?,?,?,?)";
        //2.找参数
        Object[] objects = {
                c.getCid(),c.getCname(), c.getGender(), c.getBirthday(), c.getCellphone(),
                c.getEmail(), c.getDescription()
        };
        try {
            /**
             * update(Connection conn, String sql, Object[] params)：
             * 用来执行插入、更新或删除（DML）操作。
             */
            qr.update(sql , objects);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
