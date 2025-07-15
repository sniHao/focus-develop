package com.focus.basedata.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient //开启服务发现
@ComponentScan(basePackages = {"com.focus"}) //包扫描
@EnableTransactionManagement //开启事务管理功能
@EnableAspectJAutoProxy(proxyTargetClass = true) //启用自动代理功能
@EnableScheduling //开启定时任务功能
@EnableAsync //开启异步任务功能
@SpringBootApplication
public class FocusBaseDataStartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocusBaseDataStartupApplication.class, args);
    }

}
