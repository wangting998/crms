package com.bh.crms.servlet;
import com.bh.crms.service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除客户
 * @Author WWT
 * @Date 2021/1/28
 */
@WebServlet(name = "DeleteServlet",urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取参数cid
         * 2.调用service方法
         * 3.保存到request域
         * 4.转发至map.jsp
         */
        String cid = request.getParameter("cid");
        customerService.deleteCustomer(cid);
        request.setAttribute("msg","客户删除成功");
        request.getRequestDispatcher("msg.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
