package com.bh.crms.service;

import com.bh.crms.dao.CustomerDao;
import com.bh.crms.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务层
 * @Author WWT
 * @Date 2021/1/27
 */
@Repository
public class CustomerService {
    //private CustomerDao customerDao = new CustomerDao();
    @Autowired CustomerDao customerDao;
    /**
     * 添加数据/客户
     * @param c
     */
    public void addCustomer(Customer c) {
        customerDao.addCustomer(c);
    }

    /**
     * 查询所有
     * @return
     */
    public List findAll() {
        return customerDao.findAll();
    }

    /**
     * 根据id查询客户,编辑客户前加载
     * @param cid
     * @return
     */
    public Customer load(String cid) {
        return customerDao.load(cid);
    }

    /**
     * 编辑客户,修改数据
     * @param c
     */
    public void editCustomer(Customer c) {
        customerDao.editCustomer(c);
    }

    /**
     * 根据cid删除客户
     * @param cid
     * @return
     */
    public void deleteCustomer(String cid) {
        customerDao.deleteCustomer(cid);
    }

    /**
     * 高级搜索/多条件组合查询
     * @return
     */
    public List<Customer> queryCustomer(Customer criteria) {
        return customerDao.queryCustomer(criteria);
    }

    /**
     * 查询所有--带有分页功能
     * @param index
     * @param len
     * @return
     */
    public List<Customer> findAllPage(int index,int len){
        return customerDao.findAllPage(index,len);
    }

    /**
     * 查询所有客户记录数
     * @return
     */
    public int countFindAll(){
        return customerDao.countFindAll();
    }

    /**
     * 多条件查询中的分页
     * @param criteria
     * @param index
     * @param len
     * @return
     */
    public List<Customer> queryByPage(Customer criteria,int index,int len){
        return customerDao.queryByPage(criteria,index,len);
    }

    /**
     * 条件查询中的记录总数
     * @param criteria
     * @return
     */
    public int countQuery(Customer criteria){
        return customerDao.countQueryPage(criteria);
    }
}
