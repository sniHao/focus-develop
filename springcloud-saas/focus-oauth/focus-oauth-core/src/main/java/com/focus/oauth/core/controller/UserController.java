package com.focus.oauth.core.controller;

import com.focus.oauth.core.application.UserApplicationService;
import com.focus.oauth.core.domain.req.UserLoginReq;
import com.focus.oauth.core.domain.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器 - Web业务
 *
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/user")
public class UserController {

    private final UserApplicationService userApplicationService;

    /**
     * 用户登录
     *
     * @param loginReq 登录请求
     * @return 用户信息
     */
    @PostMapping("/login")
    public UserInfoVo login(@RequestBody UserLoginReq loginReq) {
        return userApplicationService.login(loginReq);
    }

    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/current")
    public UserInfoVo getCurrentUser() {
        return userApplicationService.getCurrentUser();
    }

    /**
     * 用户登出
     *
     * @return 结果
     */
    @PostMapping("/logout")
    public String logout() {
        return userApplicationService.logout();
    }

    /**
     * 健康检查
     *
     * @return 结果
     */
    @GetMapping("/health")
    public String health() {
        return "OAuth服务运行正常";
    }
}
