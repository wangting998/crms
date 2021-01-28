package com.bh.crms.service;

import com.bh.crms.dao.CustomerDao;
import com.bh.crms.pojo.Customer;
import com.bh.crms.pojo.Customer;

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
}
