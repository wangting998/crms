package com.bh.crms.servlet;

import com.bh.crms.service.FindAllService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindAllCustomerServlet",urlPatterns = "/findAll")
public class FindAllServlet extends HttpServlet {
    private FindAllService findAllService = new FindAllService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.调用service方法
         * 2.保存到request域
         * 3.转发至list.jsp
         */
        List list = findAllService.findAll();
        request.setAttribute("list",list);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }
}
