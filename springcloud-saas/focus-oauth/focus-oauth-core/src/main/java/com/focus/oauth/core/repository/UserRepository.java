package com.focus.oauth.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focus.oauth.core.domain.entity.User;
import com.focus.oauth.core.repository.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户仓储
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Service
public class UserRepository extends ServiceImpl<UserMapper, User> {
    
    /**
     * 根据用户名查找用户
     * 
     * @param username 用户名
     * @return 用户信息
     */
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }
} 