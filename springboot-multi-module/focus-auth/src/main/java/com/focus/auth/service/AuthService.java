package com.focus.auth.service;

import com.focus.model.dto.LoginDTO;

import java.util.Map;

/**
 * 认证服务接口
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param dto 登录参数
     * @return token信息
     */
    Map<String, Object> login(LoginDTO dto);

    /**
     * 登出
     */
    void logout();
}
