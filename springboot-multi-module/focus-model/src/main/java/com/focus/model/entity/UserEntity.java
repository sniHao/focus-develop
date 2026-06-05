package com.focus.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("u_user")
public class UserEntity extends BaseEntity {

    /**
     * 用户ID（业务ID）
     */
    private Long uid;

    /**
     * 手机号
     */
    private String phone;

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
