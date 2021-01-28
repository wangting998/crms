package com.bh.crms.servlet;
import com.bh.crms.pojo.Customer;
import com.bh.crms.service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 编辑之数据加载，根据cid查询
 * @Author WWT
 * @Date 2021/1/28
 */
@WebServlet(name = "EditLoadServlet",urlPatterns = "/load")
public class EditLoadServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取参数cid
         * 2.调用service方法,通过cid查询Customer对象
         * 3.保存到request域
         * 4.转发至edit.jsp
         */
        String cid = request.getParameter("cid");
        //Customer customer = customerService.load(cid);
        request.setAttribute("customer",customerService.load(cid));
        request.getRequestDispatcher("edit.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
