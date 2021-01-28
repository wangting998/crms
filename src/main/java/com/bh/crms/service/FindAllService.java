package com.bh.crms.service;

import com.bh.crms.dao.CustomerDao;

import java.util.List;

public class FindAllService {
    private CustomerDao customerDao = new CustomerDao();
    /**
     * 查询所有
     * @return
     */
    public List findAll() {
        return customerDao.findAll();
    }
}
