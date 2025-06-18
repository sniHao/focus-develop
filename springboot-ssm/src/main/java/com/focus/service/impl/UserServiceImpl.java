package com.focus.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.focus.bean.vo.UserInfoVO;
import com.focus.common.constant.FocusResultCode;
import com.focus.common.exception.FocusException;
import com.focus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:用户业务实现类
 * @Author: ni_hao
 * @Date: 2025-06-18 15:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserInfoVO login(String username, String password) throws FocusException {
        // 模拟登录
        if (!"focus".equals(username) || !"123456".equals(password))
            throw new FocusException(FocusResultCode.USER_LOGIN_EXCEPTION.tips());
        StpUtil.login(10001L);
        return new UserInfoVO("欢迎使用Focus-Develop👋");
    }
}
