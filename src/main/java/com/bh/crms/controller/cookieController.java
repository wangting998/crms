package com.bh.crms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class cookieController {
    /**
     * cookie功能实现
     */
    @RequestMapping("/cookie")
    public String findAllPageTest(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        Cookie cookie = new Cookie("name","王文婷");
        //把生成的cookie写到响应的客户端
        response.addCookie(cookie);
        return "list_";
    }

    private static int cookieCount;

    @GetMapping(value = "/addCookies")
    public void addCookies(HttpServletResponse response) throws IOException {

        //设置contentType，解决中文乱码
        response.setContentType("text/html;charset=utf-8");

        String name = "cookie_" + (++cookieCount);
        String value = String.valueOf(System.currentTimeMillis());
        Cookie cookie = new Cookie(name, value);

        /*cookie的其他属性
        除了必填属性名称和值之外，您还可以使用以下方法为Cookie指定其他信息：
        setComment（String）：指定描述cookie用途的注释。
        例如：
        cookie.setComment("This cookie stores timestamp");
        setDomain（String）：指定在其中可见此cookie的域名。默认情况下，cookie只返回给发送它们的服务器。因此，设置域名将使Cookie可用于同一域名下的不同服务器。
        例如：
        cookie.setDomain(".csdn.net");
        该cookie将可用于域代码csdn.net下的所有服务器。请注意，域名应以点开头。下面的示例将cookie的域设置为localhost：
        cookie.setDomain("localhost");
        setHttpOnly（boolean）：如果设置为true，则Javascript无法在客户端读取此cookie，从而可以防止某些跨站点脚本攻击。
        例如：
        cookie.setHttpOnly(true);
        setMaxAge（int）：指定cookie在用户计算机中存储的时间，以秒为单位。如果未设置，则退出Web浏览器时将删除cookie。
        例如：
        cookie.setMaxAge(7 * 24 * 60 * 60);
        这将cookie的生存期设置为7天（= 24小时x 60分钟x 60秒），并且当存在浏览器时，它仍存储在用户的计算机上。
        setPath（String）：如果要限制cookie在服务器上的某个路径（及其子路径）中可用，请使用此方法。
        例如：
        cookie.setPath("/product");
        setSecure（boolean）：如果设置为true，则仅在使用安全协议（HTTPS或SSL）时将cookie从浏览器发送到服务器。默认为false。
         */
        response.addCookie(cookie);
        response.getWriter().println(name+"创建成功");
    }

    @GetMapping(value = "/readCookies")
    public void readCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置contentType，解决中文乱码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            writer.println("从request中未读取到cookie");
        } else {
            writer.println("cookie数量: " + cookies.length);

            for (Cookie aCookie : cookies) {
                String name = aCookie.getName();
                String value = aCookie.getValue();

                writer.println(name + " = " + value);
            }
        }
    }


    @GetMapping(value = "/deleteCookies")
    public void deleteCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置contentType，解决中文乱码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie aCookie : cookies) {
                aCookie.setMaxAge(0);
                response.addCookie(aCookie);
            }

            writer.println("所有cookie已删除");
        } else {
            writer.println("从request中未读取到cookie");
        }
    }
}
