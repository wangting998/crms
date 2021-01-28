package com.bh.crms.servlet;
import com.bh.crms.pojo.Customer;
import com.bh.crms.service.CustomerService;
import com.bh.crms.util.CustomerUtils;
import com.bh.crms.pojo.Customer;
import com.bh.crms.service.CustomerService;
import com.bh.crms.util.CustomerUtils;

import javax.servlet.ServletContext;
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
 * 添加数据/客户
 * @Author WWT
 * @Date 2021/1/27
 */
@WebServlet(name = "CustomerServlet",urlPatterns = "/add")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.封装对象
         * 2.添加cid--UUID
         * 3.调用service方法,完成添加
         * 4.把添加成功的信息保存到request域
         * 5.跳转至msg.jsp
         */

        /**
         * 1.封装对象
         * 获取参数
         *      String cid, String cname, String gender, Date birthday,
         *      String cellphone, String email, String description
         *      cid写死
         */
        String cid = CustomerUtils.uuid();  //2.添加cid--UUID
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
        Date date = new Date();
        //构造方法
        Customer customer = new Customer(cid,cname,gender,date1,cellphone,email,description);

        //3.调用service方法
        customerService.addCustomer(customer);

        /**
         * 4.把添加成功的信息保存到request域
         * ServletContext getServletContext()：用来获取 ServletContext 对象
         * 在 Servlet 中获取 ServletContext 对象   void init(ServletConfig config)
         */
        ServletContext servletContext = this.getServletContext();
        //void setAttribute(String name, Object value)：用来存储一个对象，也可以称之为存储一个域属

        request.setAttribute("msg","客户添加成功"); //转发时使用
        //servletContext.setAttribute("msg","客户添加成功"); //用重定向时使用

        //5.跳转至msg.jsp
        //response.sendRedirect("msg.jsp"); //重定向
        request.getRequestDispatcher("msg.jsp").forward(request,response); //转发
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
