package com.focus.bean.dto;

import lombok.Data;

/**
 * @Description: 用户登录入参
 * @Author: ni_hao
 * @Date: 2025-06-18 15:17
 */
@Data
public class UserLoginDTO {
    private String account; // 账号
    private String password; // 密码
}
