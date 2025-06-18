package com.focus.common.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:线程池配置类
 * @Author: ni_hao
 * @Date: 2025-04-23 15:40
 */
@Configurable
@EnableAsync
public class ThreadPoolConfig {
    /**
     * 获取服务器的cpu个数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int COUR_SIZE = CPU_COUNT * 2;
    private static final int MAX_COUR_SIZE = CPU_COUNT * 4;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int KEEP_ALIVE_TIME = 10;
    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX = "focus-Async-";

    /**
     * 配置线程池
     *
     * @return r
     */
    @Bean("focusExecutor")
    public ThreadPoolTaskExecutor focusExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(COUR_SIZE);// 设置核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(MAX_COUR_SIZE);// 配置最大线程数
        threadPoolTaskExecutor.setQueueCapacity(MAX_COUR_SIZE * 4);// 配置队列容量（这里设置成最大线程数的四倍）
        threadPoolTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);// 给线程池设置名称
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 设置任务的拒绝策略
        threadPoolTaskExecutor.initialize();   // 初始化
        return threadPoolTaskExecutor;
    }
}
