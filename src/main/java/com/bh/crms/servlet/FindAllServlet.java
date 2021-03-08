//package com.bh.crms.servlet;
//
//import com.bh.crms.service.CustomerService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
///**
// * 查询所有客户
// * @Author WWT
// * @Date 2021/1/27
// */
//@WebServlet(name = "FindAllServlet",urlPatterns = "/findAll")
//public class FindAllServlet extends HttpServlet {
//    private CustomerService customerService = new CustomerService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /**
//         * 1.调用service方法完成查询
//         * 2.保存数据到request域
//         * 3.转发至list.jsp
//         */
//        //List customerList = customerService.findAll();
//        request.setAttribute("list",customerService.findAll());
//        request.getRequestDispatcher("list.jsp").forward(request,response);
//    }
//}
