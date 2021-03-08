//package com.bh.crms.servlet;
//import com.bh.crms.pojo.Customer;
//import com.bh.crms.pojo.PageBean;
//import com.bh.crms.service.CustomerService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * 查询所有，分页功能实现
// * @Author WWT
// * @Date 2021/1/29
// */
//@WebServlet(name = "FindAllPageServlet",urlPatterns = "/findAllPage")
//public class FindAllPageServlet extends HttpServlet {
//    private CustomerService customerService = new CustomerService();
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        //System.out.println("init()");
//    }
//
//    @Override
//    public void destroy() {
//        super.destroy();
//        //System.out.println("destory()...");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /**
//         * 1、获pc,若pc不存在，设置pc=1
//         * 2、获取url,若url中包含&pc=,则处理之
//         * 3、获取tr
//         * 4、设置ps
//         * 5、创建PageBean
//         * 6、获取datas
//         * 7、保存PageBean至request域中
//         * 8、转发至list.jsp页面
//         */
//        //1、获取pc,即当前页码
//        // 获取分页参数
//        String pageCode = request.getParameter("pc");
//        int pc = 1;
//        if (pageCode != null && !pageCode.trim().isEmpty()) {
//            pc = Integer.parseInt(pageCode);
//        }
//
//        //2、获取url，即请求路径及参数
//        String url = request.getRequestURI() + "?" + request.getQueryString();
//        //System.out.println("url00000:"+url);
//        int lastIndex = url.lastIndexOf("&pc=");
//        if (lastIndex != -1){
//            url = url.substring(0,lastIndex);
//        }
//
//        //3、获取tr，即是总记录数
//        int tr = customerService.countFindAll();
//        //4、设置ps，即每页记录数
//        int ps = 10;
//        //5、创建PageBean
//        PageBean<Customer> list = new PageBean<>(pc,tr,ps);
//        list.setUrl(url);
//
//        //6、获取datas,即当前页记录
//        List<Customer> datas = customerService.findAllPage(list.getIndex(),ps);
//        list.setBeanList(datas);
//
//        //7、保存PageBean至request中
//        request.setAttribute("list",list);
//
//        //8、转发至list.jsp页面
//        request.getRequestDispatcher("list.jsp").forward(request,response);
//    }
//
//
//    /**
//     * 可调用多次请求
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }
//}
