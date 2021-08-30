package com.dragon.sagittal.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动类
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"com.dragon.sagittal.blog"
// })  // 启动类添加注解 使用swagger
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
