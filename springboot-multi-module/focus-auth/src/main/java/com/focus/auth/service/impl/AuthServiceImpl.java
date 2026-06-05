package com.focus.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.focus.auth.service.AuthService;
import com.focus.common.constant.FocusResultCode;
import com.focus.model.dto.LoginDTO;
import com.focus.model.entity.UserEntity;
import com.focus.common.exception.FocusException;
import com.focus.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务实现
 * 基于 Sa-Token 实现登录/登出/会话管理
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    @Override
    public Map<String, Object> login(LoginDTO dto) {
        // TODO: 实际项目中应该先校验验证码（从Redis中获取）
        // 这里简化处理，验证码固定为 "123456"
        if (!"123456".equals(dto.getCode())) {
            throw new FocusException("验证码错误", FocusResultCode.USER_LOGIN_EXCEPTION.code());
        }

        // 查询用户
        UserEntity user = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getPhone, dto.getPhone()));

        // 用户不存在则自动注册
        if (user == null) {
            user = new UserEntity();
            user.setUid(System.currentTimeMillis()); // 简化处理，实际应该用雪花算法
            user.setPhone(dto.getPhone());
            user.setName("用户" + dto.getPhone().substring(7)); // 默认昵称
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setDeleted(0);
            userMapper.insert(user);
        }

        // Sa-Token 登录，传入用户ID
        StpUtil.login(user.getUid());

        // 返回token信息
        Map<String, Object> result = new HashMap<>();
        result.put("token", StpUtil.getTokenValue());
        result.put("uid", user.getUid());
        result.put("phone", user.getPhone());
        result.put("name", user.getName());
        return result;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }
}
