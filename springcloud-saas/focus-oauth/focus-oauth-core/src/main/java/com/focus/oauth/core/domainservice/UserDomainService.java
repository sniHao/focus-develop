package com.focus.oauth.core.domainservice;

import cn.hutool.core.bean.BeanUtil;
import com.focus.oauth.core.domain.entity.User;
import com.focus.oauth.core.domain.req.UserLoginReq;
import com.focus.oauth.core.domain.vo.UserInfoVo;
import com.focus.oauth.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户领域服务
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDomainService {
    
    private final UserRepository userRepository;
    
    /**
     * 用户登录
     * 
     * @param loginReq 登录请求
     * @return 用户信息
     */
    public UserInfoVo login(UserLoginReq loginReq) {
        // 模拟登录逻辑
        User user = userRepository.findByUsername(loginReq.getUsername());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 简化密码验证（实际应该使用加密验证）
        if (!loginReq.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        
        // 更新最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.updateById(user);
        
        // 构建返回数据
        UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
        userInfoVo.setUserId(user.getId());
        userInfoVo.setAccessToken("access_token_" + user.getId());
        userInfoVo.setRefreshToken("refresh_token_" + user.getId());
        userInfoVo.setTokenExpireTime(LocalDateTime.now().plusHours(2));
        
        return userInfoVo;
    }
    
    /**
     * 获取当前用户信息
     * 
     * @return 用户信息
     */
    public UserInfoVo getCurrentUser() {
        // 模拟获取当前用户（实际应该从token中获取）
        User user = userRepository.findByUsername("admin");
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        UserInfoVo userInfoVo = BeanUtil.copyProperties(user, UserInfoVo.class);
        userInfoVo.setUserId(user.getId());
        return userInfoVo;
    }
    
    /**
     * 用户登出
     * 
     * @return 结果
     */
    public String logout() {
        // 模拟登出逻辑（实际应该清理token）
        return "登出成功";
    }
} 