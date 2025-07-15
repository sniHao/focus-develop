package com.focus.oauth.api.api;

import com.focus.basedata.api.dto.BaseDataDTO;
import com.focus.basedata.api.dto.UserInfoDTO;
import com.focus.oauth.core.service.BaseDataConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户远程调用API
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth/user")
public class UserApi {
    
    private final BaseDataConsumerService baseDataConsumerService;
    
    /**
     * 通过远程调用获取用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable Long userId) {
        return baseDataConsumerService.getUserInfo(userId);
    }
    
    /**
     * 通过远程调用检查用户是否存在
     * 
     * @param userId 用户ID
     * @return 是否存在
     */
    @GetMapping("/check/{userId}")
    public boolean checkUserExists(@PathVariable Long userId) {
        return baseDataConsumerService.checkUserExists(userId);
    }
} 