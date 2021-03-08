package com.bh.crms.controller;

import com.bh.crms.pojo.Thymeleaf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 控制层
 * 创建ThymeleafController用于测试后台 设置数据到model中
 * @Author WWT
 * @Date 2021/3/3
 */
@Controller
@RequestMapping("/th")
public class ThymeleafController {

    @RequestMapping("/test")
    public String testThymeleaf(Model model) {
        /**
         * th:action 后台控制器路径
         */
        model.addAttribute("test_thymeleaf","测试thymeleaf模板...");

        /**
         * th:each 对象遍历
         */
        List<Thymeleaf> thymeleaves = new ArrayList<>();
        thymeleaves.add(new Thymeleaf(1001,"张三","大明湖畔"));
        thymeleaves.add(new Thymeleaf(1002,"李思","趵突泉变"));
        thymeleaves.add(new Thymeleaf(1003,"王五","杨柳深处"));

        model.addAttribute("thymeleaves",thymeleaves);
        model.addAttribute("test_thymeleaf","测试thymeleaf模板...");

        /**
         * Map 添加数据
         */
        Map<String,Object> datamap = new HashMap<>();
        datamap.put("No","1001");
        datamap.put("address","大明湖畔");
        datamap.put("phone","13698622007");
        model.addAttribute("datamap",datamap);

        /**
         * 数组 添加代码
         */
        String[] array = {"李思","周涛","李梓","康组长"};
        model.addAttribute("array",array);

        /**
         * Date 添加代码
         */
        model.addAttribute("now",new Date());

        /**
         * th:if条件 添加代码
         */
        model.addAttribute("age",22);


        return "Demo";
    }
}
