package com.focus.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户更新DTO
 */
@Data
public class UserUpdateDTO {

    @NotNull(message = "用户ID不能为空")
    private Long uid;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    private String photo;

    /**
     * 性别（0：女  1：男）
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;
}
