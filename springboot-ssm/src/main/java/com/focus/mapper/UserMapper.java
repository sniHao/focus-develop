package com.focus.mapper;

import com.focus.bean.po.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户Mapper接口
 * @Author: ni_hao
 * @Date: 2025-06-18 15:14
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {
}
