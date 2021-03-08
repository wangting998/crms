package com.bh.crms.servlet;

import com.bh.crms.pojo.Customer;
import com.bh.crms.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 高级搜索/多条件组合查询
 * @Author WWT
 * @Date 2021/1/28
 */
@WebServlet(name = "QueryServlet",urlPatterns = "/query")
public class QueryServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.封装对象，最多有4个属性   Criteria：接口类
         * 2.调用service方法，保存信息
         * 3.保存查询结果到request域
         * 4.转发至list.jsp
         */
        //获取参数
        String cname = request.getParameter("cname");
        String gender = request.getParameter("gender");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        //1.封装对象
        Customer criteria = new Customer(cname,gender,cellphone,email);
        //2.调用service对象
        //List<Customer> criteriaList = customerService.query(criteria);
        //3.保存到request域
        request.setAttribute("list",customerService.queryCustomer(criteria));
        //4.转发至list.jsp
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
