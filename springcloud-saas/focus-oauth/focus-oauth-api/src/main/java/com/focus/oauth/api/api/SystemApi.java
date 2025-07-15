package com.focus.oauth.api.api;

import com.focus.oauth.core.service.BaseDataConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置远程调用API
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth/system")
public class SystemApi {
    
    private final BaseDataConsumerService baseDataConsumerService;
    
    /**
     * 通过远程调用获取系统配置
     * 
     * @param configKey 配置key
     * @return 配置值
     */
    @GetMapping("/config/{configKey}")
    public String getSystemConfig(@PathVariable String configKey) {
        return baseDataConsumerService.getSystemConfig(configKey);
    }
} 