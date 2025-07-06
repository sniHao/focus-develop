package com.focus.infrastructure.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("a_admin")
public class AdminPO {
    @TableId(type = IdType.AUTO)
    private Long id;
}