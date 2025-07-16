package com.focus.oauth.core.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.focus.oauth.core.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
} 