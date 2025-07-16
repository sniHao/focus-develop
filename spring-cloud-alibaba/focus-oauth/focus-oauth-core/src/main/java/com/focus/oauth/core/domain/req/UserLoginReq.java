package com.focus.oauth.core.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录请求
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Data
public class UserLoginReq {
    
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 验证码
     */
    private String captcha;
    
    /**
     * 记住我
     */
    private Boolean rememberMe;
} 