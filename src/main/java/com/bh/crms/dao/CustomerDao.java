package com.bh.crms.dao;

import com.bh.crms.pojo.Customer;
import com.bh.crms.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author WWT
 * @Date 2021/1/27
 */
@Repository
public class CustomerDao {
    /**
     * 获取数据源
     * QreryRunner类(org.apache.commons.dbutils.QueryRunner) 是Dbutils的核心类之一
     * 它简化了SQL查询，并与ResultSetHandler协同工作将使编码量大为减少
     */
    private static QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

    /**
     * 多条件组合查询中的分页
     *
     * @param criteria
     * @param index
     * @param len
     * @return
     */
    public List<Customer> queryByPage(Customer criteria, int index, int len) {
        StringBuilder sql = new StringBuilder("select * from tb_customer where 1=1");
        List<Object> params = new ArrayList<>();

        String cname = criteria.getCname();
        if (cname != null && !cname.trim().isEmpty()) {
            sql.append(" and cname like ?");
            params.add("%" + cname + "%");

        }

        String gender = criteria.getGender();
        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" and gender=?");
            params.add(gender);

        }

        String email = criteria.getEmail();
        if (email != null && !email.trim().isEmpty()) {
            sql.append(" and email like ?");
            params.add("%" + email + "%");

        }

        String cellphone = criteria.getCellphone();
        if (cellphone != null && !cellphone.trim().isEmpty()) {
            sql.append(" and cellphone like ?");
            params.add("%" + cellphone + "%");
        }

        sql.append(" limit ?,?");
        params.add(index);
        params.add(len);
        try {
            return qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 多条件查询中记录总数
     *
     * @return
     */
    public int countQueryPage(Customer criteria) {
        StringBuilder sql = new StringBuilder("select count(*) from tb_customer where 1=1");
        List<Object> params = new ArrayList<>();
        String cname = criteria.getCname();
        if (cname != null && !cname.trim().isEmpty()) {
            sql.append(" and cname like ?");
            params.add("%" + cname + "%");

        }

        String gender = criteria.getGender();
        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" and gender=?");
            params.add(gender);

        }

        String cellphone = criteria.getCellphone();
        if (cellphone != null && !cellphone.trim().isEmpty()) {
            sql.append(" and cellphone like ?");
            params.add("%" + cellphone + "%");

        }

        String email = criteria.getEmail();
        if (email != null && !email.trim().isEmpty()) {
            sql.append(" and email like ?");
            params.add("%" + email + "%");

        }
        try {
            Long cnt = (Long) qr.query(sql.toString(), new ScalarHandler(), params.toArray());
            return cnt.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有客户记录数
     *
     * @return
     */
    public int countFindAll() {
        /**
         * 1.给出sql
         * 2.调用方法
         */
        String sql = "select count(*) from tb_customer";
        try {
            Long cnt = qr.query(sql, new ScalarHandler<>());
            return cnt.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 查询所有--带有分页功能
     *
     * @param index
     * @param len
     * @return
     */
    public List<Customer> findAllPage(int index, int len) {
        /**
         * 1.给出sql
         * 2.调用方法
         */
        String sql = "select * from tb_customer limit ?,?";
        try {
            return qr.query(sql, new BeanListHandler<>(Customer.class), index, len);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 高级搜索/多条件组合查询,不带分页
     * @param criteria
     * @return
     */
    public List<Customer> queryCustomer(Customer criteria) {
        /**
         * 1.拼接sql
         * 2.找参数
         * 3.调用方法
         */
        try {
            //拼接的sql语句
            StringBuffer sql =  new StringBuffer("select * from tb_customer where 1=1");
            //保存参数的容器
            List<Object> criteriaList = new ArrayList<>();
            //判断条件
            String cname = criteria.getCname();
            if(cname != null && !cname.trim().isEmpty()){
                //若不为空
                //1、sql后拼接and cname =?   模糊查询like
                //CONCAT（）函数用于将多个字符串连接成一个字符串
                sql.append(" and cname like concat('%',?,'%')");
                //2、参数保存
                criteriaList.add(cname);
            }
            String gender = criteria.getGender();
            if (gender != null && !gender.trim().isEmpty()){
                //若不为空
                //1、sql后拼接and gender =?
                sql.append(" and gender = ?");
                //2、参数保存
                criteriaList.add(gender);
            }
            String cellphone = criteria.getCellphone();
            if (cellphone != null && !cellphone.trim().isEmpty()){
                //若不为空
                //1、sql后拼接and cellphone =?
                sql.append(" and cellphone like ?");
                //list.add("%" + cellphone +"%");
                //2、参数保存
                criteriaList.add(cellphone);
            }
            String email = criteria.getEmail();
            if(email != null && !email.trim().isEmpty()){
                //若不为空
                //1.sql后拼接and email =?
                sql.append(" and email like ?");
                //list.add("%" + email +"%");
                //2、参数保存
                criteriaList.add(email);
            }
            //3.调用方法
            return qr.query(sql.toString(),new BeanListHandler<>(Customer.class),criteriaList.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
         * 3、update()
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        try {
            /**
             * BeanHandler：单行处理器！把结果集转换成 Bean，
             * 该处理器需要 Class 参数，即 Bean 的类 型；
             */
            return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有
     * @return
     */
    public List<Customer> findAll() {
        /**
         * 1.给出sql
         * 2.找参数
         * 3.存放容器
         */
        try {
            String sql = "select * from tb_customer where ebable = 0";
            //BeanListHandler：多行处理器！把结果集转换成 List<Bean>；
            //query()：执行 select 语句
            return qr.query(sql, new BeanListHandler<>(Customer.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
