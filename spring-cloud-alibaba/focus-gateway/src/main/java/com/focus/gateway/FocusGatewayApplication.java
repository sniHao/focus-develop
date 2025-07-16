package com.focus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Focus Gateway 网关服务启动类
 *
 * @author focus
 * @date 2024-07-16
 */
@EnableDiscoveryClient  // 开启服务发现
@ComponentScan(basePackages = {"com.focus"})  // 包扫描
@SpringBootApplication
public class FocusGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocusGatewayApplication.class, args);
        System.out.println("\n======================================================================");
        System.out.println("            Focus Gateway 网关服务启动成功");
        System.out.println("            网关地址: http://localhost:10010");
        System.out.println("======================================================================\n");
    }
}
