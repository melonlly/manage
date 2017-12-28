package com.manage;

import com.manage.user.dao.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@Configuration ：标明该类使用Spring基于Java的配置
//@ComponentScan ：启用组件扫描
//@EnableAutoConfiguration ：开启spring boot的自动配置
//@SpringBootApplication 注解代表的是以上三个注解的组合
@SpringBootApplication      //开启组件扫描和自动配置
@EnableAutoConfiguration(exclude = {})
public class ManageApplication {

    //负责自动和引导应用程序
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
