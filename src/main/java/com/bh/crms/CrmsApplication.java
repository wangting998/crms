package com.bh.crms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ServletComponentScan()
//@ServletComponentScan(basePackages = "com.bh.crms") //在SpringBoot启动会自动扫描@WebServlet，将该类实例化
public class CrmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmsApplication.class, args);
    }
}
