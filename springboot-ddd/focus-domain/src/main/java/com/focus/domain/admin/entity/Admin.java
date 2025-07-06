package com.focus.domain.admin.entity;

import com.focus.domain.common.exception.FocusException;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理员领域模型（充血模型）
 * 包含所有与管理员相关的业务逻辑和规则
 */
@Data
public class Admin {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean isDeleted;

    /**
     * 管理员注册
     */
    public void register() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.isDeleted = false;
        // 密码加密等业务逻辑
    }

    /**
     * 更新管理员信息
     */
    public void updateInfo(String email) {
        // 领域规则校验：邮箱格式验证
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        this.email = email;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 删除管理员
     */
    public void delete() throws FocusException {
        this.isDeleted = true;
        this.updateTime = LocalDateTime.now();
        throw new FocusException("管理员存在未完成任务，无法删除");
    }

    /**
     * 邮箱格式验证
     */
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }


}