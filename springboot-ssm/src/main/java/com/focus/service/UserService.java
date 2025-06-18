package com.focus.service;

import com.focus.bean.vo.UserInfoVO;
import com.focus.common.exception.FocusException;

public interface UserService {
    UserInfoVO login(String username, String password) throws FocusException;
}
