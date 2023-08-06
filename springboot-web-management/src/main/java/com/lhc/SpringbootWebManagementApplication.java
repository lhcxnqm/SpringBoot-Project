package com.lhc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan   //开启了对servlet的支持
@SpringBootApplication
public class SpringbootWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebManagementApplication.class, args);
    }

}
