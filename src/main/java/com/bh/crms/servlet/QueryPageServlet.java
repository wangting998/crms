package com.bh.crms.servlet;

import com.bh.crms.pojo.Customer;
import com.bh.crms.pojo.PageBean;
import com.bh.crms.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 多条件查询中的分页
 * @Author: WWT
 * @Date: 2021/3/1
 */
@WebServlet(name = "ServletQueryPage", urlPatterns = "/queryPage")
public class QueryPageServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1、获取pc,若pc不存在，设置pc=1
         * 2、获取url,若url中包含了&pc=,则处理之
         * 3、获取表单数据
         * 4、获取tr
         * 5、设置ps
         * 6、创建pageBean
         * 7、获取datas
         * 8、保存PageBean到request域中
         * 9、转发至list.jsp页面
         */
        //1、获取pc,即当前码
        String pageCode = request.getParameter("pc");
        int pc = 1;
        if (pageCode != null && !pageCode.trim().isEmpty()) {
            pc = Integer.parseInt(pageCode);
        }

        //2、获取url,即请求路径及参数
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int lastIndex = url.lastIndexOf("&pc=");
        if (lastIndex != -1) {
            url = url.substring(0, lastIndex);
        }

        //3、获取表单数据
        String cname = request.getParameter("cname");
        String gender = request.getParameter("gender");
        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        Customer criteria = new Customer(cname,gender,cellphone,email);

        //4、获取tr,即总记录数
        int tr = customerService.countQuery(criteria);

        //5、设置ps,即每页记录数
        int ps = 10;

        //6、创建PageBean
        PageBean<Customer> list = new PageBean<>(pc, tr, ps);
        list.setUrl(url);

        //7、获取datas,即当前页记录
        List<Customer> datas = customerService.queryByPage(criteria, list.getIndex(), ps);
        list.setBeanList(datas);

        //8、保存PageBean至request域中
        request.setAttribute("list", list);

        //9、转发至list.jsp
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
