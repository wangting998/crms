package com.bh.crms.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author WWT
 * @Date 2021/3/3
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @RequestMapping("/index")
    public String testJsp(){
        return "/index";
    }
}
