package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableCaching //开启缓存
@EnableScheduling // 开启定时任务
public class SkyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SkyApplication.class, args);
        // 获取当前项目端口号
        Environment env = context.getBean(Environment.class);
        log.info("外卖启动QAQ");
        log.info("swagger地址：http://localhost:{}/doc.html", env.getProperty("server.port"));
    }
}
