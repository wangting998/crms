package com.bh.crms.dao;

import com.bh.crms.pojo.Customer;
import com.bh.crms.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
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
     * 高级搜索
     * @param criteria
     * @return
     */
    public List<Customer> query(Customer criteria) {
        /**
         * 1.给出sql
         * 2.找参数
         * 3.调用方法
         */
        StringBuffer sql =  new StringBuffer("select * from tb_customer where 1=1");
        //保存参数的容器
        List<Object> list = new ArrayList<>();
        String cname = criteria.getCname();
        //判断条件
        if(cname != null && !cname.trim().isEmpty()){
            //若不为空
            //1、sql后拼接and cname =?
            sql.append(" and cname like concat('%',?,'%')");
            //2、参数保存
            list.add(cname);
        }
        String gender = criteria.getGender();
        if (gender != null && !gender.trim().isEmpty()){
            //若不为空
            //1、sql后拼接and gender =?
            sql.append(" and gender = ?");
            //2、参数保存
            list.add(gender);
        }
        String cellphone = criteria.getCellphone();
        if (cellphone != null && !cellphone.trim().isEmpty()){
            //若不为空
            //1、sql后拼接and cellphone =?
            sql.append(" and cellphone like ?");
            //list.add("%" + cellphone +"%");
            //2、参数保存
            list.add(cellphone);
        }
        String email = criteria.getEmail();
        if(email != null && !email.trim().isEmpty()){
            //若不为空
            //1.sql后拼接and email =?
            sql.append(" and email like ?");
            //list.add("%" + email +"%");
            //2、参数保存
            list.add(email);
        }


        try {
            //3.调用方法
            return qr.query(sql.toString(),new BeanListHandler<>(Customer.class),list.toArray());
        } catch (SQLException e) {
            System.out.println("拼接失败");
        }
        throw new RuntimeException();
    }

    /**
     * 根据cid删除客户
     * @param
     */
    public void deleteCustomer(String cid) {
        /**
         * 1.给出sql
         *      逻辑删除--表中加字段  ebable=1 表已删除数据  ebable=0表正常数据
         *      upsate 表名 set ebable=1 WHERE cid=?
         * 2.找参数
         */
        String sql = "update tb_customer set ebable = 1 WHERE cid = ?";
        //parseInt() 方法用于将字符串参数作为有符号的十进制整数进行解析。
        try {
            qr.update(sql, cid);
        } catch (SQLException throwables) {
            System.out.println("删除语句不成功");
        }
    }


    /**
     * 编辑客户,修改数据
     * @param c
     */
    public void editCustomer(Customer c) {
        /**
         * 1.给出sql
         * 2.找参数
         */
        //1.给出sql
        String sql = "update tb_customer set cname=?,gender=?,birthday=?,\n" +
                "cellphone=?,email=?,description=?, where cid=?;";
        //2.找参数
        Object[] objects = {
                c.getCname(), c.getGender(), c.getBirthday(), c.getCellphone(),
                c.getEmail(), c.getDescription(),c.getCid()
        };
        try {
            qr.update(sql,objects);
        } catch (SQLException throwables) {
            System.out.println("修改语句执行不成功");
        }
    }

    /**
     * 根据cid查询客户,加载数据
     * @param cid
     * @return
     */
    public static Customer load(String cid) {
        /**
         * 1.给出sql
         * 2.找参数
         * 3.存放容器
         */
        String sql = "select * from tb_customer where cid = ?";
        Customer customer= null;
        try {
            /**
             * BeanHandler：单行处理器！把结果集转换成 Bean，
             * 该处理器需要 Class 参数，即 Bean 的类 型；
             */
            customer= qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
        } catch (SQLException throwables) {
            System.out.println("根据id查询客户查询失败");
        }
        return customer;
    }

    /**
     * 查询所有
     * @return
     */
    public static List findAll() {
        /**
         * 1.给出sql
         * 2.找参数
         * 3.存放容器
         */
        String sql = "select * from tb_customer where ebable = 0";
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
        String sql = "insert into tb_customer values (?,?,?,?,?,?,?,?)";
        //2.找参数
        Object[] objects = {
                c.getCid(),c.getCname(), c.getGender(), c.getBirthday(), c.getCellphone(),
                c.getEmail(), c.getDescription() , c.getEbable()
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
