package com.focus.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户实体类
 * @Author: ni_hao
 * @Date: 2025-06-18 15:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("f_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String account; // 账号
    private String password; // 密码
}
