package com.bh.crms.controller;

import com.bh.crms.pojo.Customer;
import com.bh.crms.pojo.PageBean;
import com.bh.crms.service.CustomerService;
import com.bh.crms.util.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @Author WWT
 * @Date 2021/3/3
 */
@Controller
public class CustomerControllerTest {

    @Autowired
    private CustomerService customerService;

    /**
     * 查询所有，分页功能实现
     */
    @RequestMapping("/findAllPage")
    public String findAllPageTest(HttpServletRequest request, HttpServletResponse response, Model model) {
        /**
         * 1、获pc,若pc不存在，设置pc=1
         * 2、获取url,若url中包含&pc=,则处理之
         * 3、获取tr
         * 4、设置ps
         * 5、创建PageBean
         * 6、获取datas
         * 7、保存PageBean至request域中
         * 8、转发至list.jsp页面
         */

//        Cookie cookie = new Cookie("name","王文婷");
//        response.addCookie(cookie);

        //1、获取pc,即当前页码
        // 获取分页参数
        String pageCode = request.getParameter("pc");
        int pc = 1;
        if (pageCode != null && !pageCode.trim().isEmpty()) {
            pc = Integer.parseInt(pageCode);
        }

        //2、获取url，即请求路径及参数
        String url = request.getRequestURI() + "?" + request.getQueryString();
        //System.out.println("url00000:"+url);
        int lastIndex = url.lastIndexOf("&pc=");
        if (lastIndex != -1){
            url = url.substring(0,lastIndex);
        }

        //3、获取tr，即是总记录数
        int tr = customerService.countFindAll();
        //4、设置ps，即每页记录数
        int ps = 10;
        //5、创建PageBean
        PageBean<Customer> list = new PageBean<>(pc,tr,ps);
        list.setUrl(url);

        //6、获取datas,即当前页记录
        List<Customer> datas = customerService.findAllPage(list.getIndex(),ps);
        list.setBeanList(datas);

        model.addAttribute("list",list);
        return "list";
    }

    /**
     * 查询所有的数据 使用 ModelAndView   没出来信息
     */
    /*@RequestMapping("/findAll")
    public ModelAndView findAllTest() {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> list= customerService.findAll();
        modelAndView.addObject(list);
        modelAndView.setViewName("list");

        return modelAndView;
    }*/

    /**
     * 查询所有的数据  使用Model
     */
    @RequestMapping("/findAll")
    public String findAllTest(Model model) {
        /**
         * 调用方法完成数据查询并返回数据
         */
        List<Customer> list= customerService.findAll();
        model.addAttribute("list",list);
        return "list";
    }


    /**
     * 添加数据/客户
     */
    @RequestMapping("/add")
    public String addCustomerTest(HttpServletRequest request, Model model) {
        /**
         * 获取参数
         *      String cid, String cname, String gender, Date birthday,
         *      String cellphone, String email, String description
         */
        String cid = CustomerUtils.uuid();  //2.添加cid--UUID
        String cname = request.getParameter("cname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");

        String cellphone = request.getParameter("cellphone");
        String email = request.getParameter("email");
        String description = request.getParameter("description");
        //Date date = new Date();   封装工具类后调用
        //构造方法
        Customer customer = new Customer(cid,cname,gender,birthday,cellphone,email,description,"0");

        //3.调用service方法
        customerService.addCustomer(customer);

        model.addAttribute("msg","客户添加成功");
        return "msg";
    }
}
