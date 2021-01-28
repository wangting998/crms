package com.bh.crms.servlet;

import com.bh.crms.pojo.Customer;
import com.bh.crms.service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 编辑客户,修改数据
 * @Author WWT
 * @Date 2021/1/28
 */
@WebServlet(name = "EditServlet",urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.封装表单数据到Customer对象
         * 2.调用service方法,完成修改后的添加
         * 3.把修改完的信息保存到request域
         * 4.转发至msg.jsp
         */
        //获取参数
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        //日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            //parse()是SimpleDateFomat里面的方法
            date1 = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            System.out.println("转换类型失败");
        }
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        String description = request.getParameter("description");

        //1.封装数据到Customer对象
        Customer customer = new Customer(cid,cname,gender,date1,cellphone,email,description);
        //2.调用service方法
        customerService.editCustomer(customer);

        /**
         * 3.把添加成功的信息保存到request域
         * void setAttribute(String name, Object value)：用来存储一个对象，也可以称之为存储一个域属
         */
        request.setAttribute("msg","客户修改成功"); //转发时使用

        //4.跳转至msg.jsp
        request.getRequestDispatcher("msg.jsp").forward(request,response); //转发
    }
}
