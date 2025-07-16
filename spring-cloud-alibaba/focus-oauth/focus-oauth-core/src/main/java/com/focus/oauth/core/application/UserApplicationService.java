package com.focus.oauth.core.application;

import com.focus.oauth.core.domain.req.UserLoginReq;
import com.focus.oauth.core.domain.vo.UserInfoVo;
import com.focus.oauth.core.domainservice.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户应用服务
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationService {
    
    private final UserDomainService userDomainService;
    
    /**
     * 用户登录
     * 
     * @param loginReq 登录请求
     * @return 用户信息
     */
    public UserInfoVo login(UserLoginReq loginReq) {
        log.info("用户登录请求，用户名：{}", loginReq.getUsername());
        return userDomainService.login(loginReq);
    }
    
    /**
     * 获取当前用户信息
     * 
     * @return 用户信息
     */
    public UserInfoVo getCurrentUser() {
        log.info("获取当前用户信息");
        return userDomainService.getCurrentUser();
    }
    
    /**
     * 用户登出
     * 
     * @return 结果
     */
    public String logout() {
        log.info("用户登出");
        return userDomainService.logout();
    }
} 