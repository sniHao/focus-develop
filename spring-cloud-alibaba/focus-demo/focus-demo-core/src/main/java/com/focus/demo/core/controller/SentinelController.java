package com.focus.demo.core.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel控制器 - 用于测试Sentinel的熔断降级等功能
 *
 * @author focus
 */
@RestController
@RequestMapping("/demo/sentinel")
public class SentinelController {

    /**
     * 测试Sentinel流控功能
     * 使用@SentinelResource注解标记资源点，并指定blockHandler处理BlockException异常
     *
     * @param name 姓名参数
     * @return 返回结果
     */
    @GetMapping("/flowControl")
    @SentinelResource(value = "flowControl", blockHandler = "flowControlBlockHandler")
    public String flowControl(@RequestParam(value = "name", required = false) String name) {
        return "Hello, " + (name == null ? "World" : name) + "! 流控测试正常响应";
    }

    /**
     * 流控降级处理方法，当触发流控规则时调用
     * 注意：blockHandler方法的参数和返回值必须与原方法一致，最后加一个BlockException参数
     *
     * @param name 姓名参数
     * @param e    BlockException异常
     * @return 降级响应
     */
    public String flowControlBlockHandler(String name, BlockException e) {
        return "触发流控！当前访问人数过多，请稍后再试。异常类型: " + e.getClass().getSimpleName();
    }

    /**
     * 测试Sentinel熔断降级功能
     * 使用@SentinelResource注解标记资源点，并指定fallback处理业务异常
     *
     * @param number 传入数字
     * @return 返回结果
     */
    @GetMapping("/circuitBreaking")
    @SentinelResource(value = "circuitBreaking", fallback = "circuitBreakingFallback")
    public String circuitBreaking(@RequestParam(value = "number", required = false) Integer number) {
        // 模拟业务异常，当传入数字为0时抛出异常
        if (number != null && number == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return "熔断测试正常响应，输入数字: " + number;
    }

    /**
     * 熔断降级处理方法，当业务抛出异常时调用
     * 注意：fallback方法的参数和返回值必须与原方法一致，最后可以加一个Throwable参数接收异常
     *
     * @param number 传入数字
     * @param t      业务异常
     * @return 降级响应
     */
    public String circuitBreakingFallback(Integer number, Throwable t) {
        return "触发熔断！系统出现异常，请稍后再试。异常信息: " + t.getMessage();
    }

    /**
     * 测试Sentinel热点参数限流功能
     *
     * @param productId 商品ID参数
     * @param userId    用户ID参数
     * @return 返回结果
     */
    @GetMapping("/hotParamFlow")
    @SentinelResource(value = "hotParamFlow", blockHandler = "hotParamFlowBlockHandler")
    public String hotParamFlow(@RequestParam(value = "productId", required = false) Long productId,
                               @RequestParam(value = "userId", required = false) Long userId) {
        return "热点参数限流测试正常响应，商品ID: " + productId + ", 用户ID: " + userId;
    }

    /**
     * 热点参数限流降级处理方法
     *
     * @param productId 商品ID参数
     * @param userId    用户ID参数
     * @param e         BlockException异常
     * @return 降级响应
     */
    public String hotParamFlowBlockHandler(Long productId, Long userId, BlockException e) {
        return "触发热点参数限流！当前商品访问人数过多，请稍后再试。商品ID: " + productId;
    }

    /**
     * 测试Sentinel系统自适应保护功能
     *
     * @return 返回结果
     */
    @GetMapping("/systemProtection")
    @SentinelResource(value = "systemProtection", blockHandler = "systemProtectionBlockHandler")
    public String systemProtection() {
        // 模拟复杂业务处理，消耗系统资源
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "系统自适应保护测试正常响应";
    }

    /**
     * 系统自适应保护降级处理方法
     *
     * @param e BlockException异常
     * @return 降级响应
     */
    public String systemProtectionBlockHandler(BlockException e) {
        return "触发系统保护！当前系统负载过高，请稍后再试。";
    }
}
