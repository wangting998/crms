package com.bh.crms.service;

import com.bh.crms.dao.CustomerDao;
import com.bh.crms.pojo.Customer;
import java.util.List;

/**
 * 业务层
 * @Author WWT
 * @Date 2021/1/27
 */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();


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
     * 高级搜索
     * @return
     */
    public List<Customer> query(Customer criteria) {
        return customerDao.query(criteria);
    }
}
