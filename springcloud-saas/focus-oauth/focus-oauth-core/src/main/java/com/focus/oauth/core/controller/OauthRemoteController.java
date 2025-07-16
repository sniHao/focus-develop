package com.focus.oauth.core.controller;

import com.focus.oauth.api.dto.BaseDataDTO;
import com.focus.oauth.api.dto.UserInfoDTO;
import com.focus.oauth.api.service.BaseDataConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OAuth远程调用控制器 - 合并所有远程调用API
 *
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthRemoteController {

    private final BaseDataConsumerService baseDataConsumerService;

    // ==================== 用户相关远程调用 ====================

    /**
     * 通过远程调用获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/user/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable Long userId) {
        return baseDataConsumerService.getUserInfo(userId);
    }

    /**
     * 通过远程调用检查用户是否存在
     *
     * @param userId 用户ID
     * @return 是否存在
     */
    @GetMapping("/user/check/{userId}")
    public boolean checkUserExists(@PathVariable Long userId) {
        return baseDataConsumerService.checkUserExists(userId);
    }

    // ==================== 基础数据相关远程调用 ====================

    /**
     * 通过远程调用获取所有基础数据
     *
     * @return 基础数据列表
     */
    @GetMapping("/basedata/all")
    public List<BaseDataDTO> getAllBaseData() {
        return baseDataConsumerService.getAllBaseData();
    }

    /**
     * 通过远程调用根据类型获取基础数据
     *
     * @param type 数据类型
     * @return 基础数据列表
     */
    @GetMapping("/basedata/type/{type}")
    public List<BaseDataDTO> getBaseDataByType(@PathVariable String type) {
        return baseDataConsumerService.getBaseDataByType(type);
    }

    // ==================== 系统配置相关远程调用 ====================

    /**
     * 通过远程调用获取系统配置
     *
     * @param configKey 配置key
     * @return 配置值
     */
    @GetMapping("/system/config/{configKey}")
    public String getSystemConfig(@PathVariable String configKey) {
        return baseDataConsumerService.getSystemConfig(configKey);
    }
}
