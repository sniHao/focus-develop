package com.focus.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.focus.common.constant.FocusResultCode;
import com.focus.common.exception.FocusException;
import com.focus.model.dto.UserUpdateDTO;
import com.focus.model.entity.UserEntity;
import com.focus.model.mapper.UserMapper;
import com.focus.model.vo.UserVO;
import com.focus.user.convert.UserConvert;
import com.focus.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务实现
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserConvert userConvert;

    @Override
    public UserVO getUserInfo(Long uid) {
        UserEntity entity = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUid, uid));
        if (entity == null) {
            throw new FocusException("用户不存在", FocusResultCode.CLIENT_ERROR.code());
        }
        return userConvert.toVO(entity);
    }

    @Override
    public void updateUserInfo(UserUpdateDTO dto) {
        UserEntity entity = userMapper.selectOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUid, dto.getUid()));
        if (entity == null) {
            throw new FocusException("用户不存在", FocusResultCode.CLIENT_ERROR.code());
        }

        // 只更新非空字段
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getPhoto() != null) {
            entity.setPhoto(dto.getPhoto());
        }
        if (dto.getGender() != null) {
            entity.setGender(dto.getGender());
        }
        if (dto.getAddress() != null) {
            entity.setAddress(dto.getAddress());
        }
        entity.setUpdateDate(new Date());

        userMapper.updateById(entity);
    }
}
