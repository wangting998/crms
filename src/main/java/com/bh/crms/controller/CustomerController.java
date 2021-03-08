package com.bh.crms.controller;

import com.bh.crms.pojo.Customer;
import com.bh.crms.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WWT
 * @Date 2021/3/2
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping("findAll")
    public Result findAllAndPage() {
        //查询到的结果添加到某一类中
        /**
         * List<Customer>
         */
        //查询到的当前页数据
        List<Customer> list = new ArrayList<>();
        //查询到的结果集保存到容器中
        Result result= new Result(2000,"查询成功",list);
        return result;
    }
}
