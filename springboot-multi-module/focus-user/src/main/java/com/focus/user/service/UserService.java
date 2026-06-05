package com.focus.user.service;

import com.focus.model.dto.UserUpdateDTO;
import com.focus.model.vo.UserVO;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 根据用户ID查询用户信息
     *
     * @param uid 用户ID
     * @return 用户信息
     */
    UserVO getUserInfo(Long uid);

    /**
     * 更新用户信息
     *
     * @param dto 更新DTO
     */
    void updateUserInfo(UserUpdateDTO dto);
}
