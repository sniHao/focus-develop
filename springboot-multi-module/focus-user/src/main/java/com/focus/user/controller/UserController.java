package com.focus.user.controller;

import com.focus.auth.annotation.FocusLoginUser;
import com.focus.model.dto.UserUpdateDTO;
import com.focus.model.vo.UserVO;
import com.focus.common.result.FocusResult;
import com.focus.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 演示：@FocusLoginUser 鉴权 + 用户信息管理
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户接口", description = "用户信息管理")
public class UserController {

    private final UserService userService;

    /**
     * 获取当前登录用户信息
     * 演示 @FocusLoginUser 自动注入当前登录用户ID
     */
    @GetMapping("/info")
    @Operation(summary = "获取当前登录用户信息")
    public FocusResult<UserVO> getUserInfo(@FocusLoginUser Long uid) {
        return FocusResult.success(userService.getUserInfo(uid));
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    public FocusResult<Void> updateUserInfo(@Valid @RequestBody UserUpdateDTO dto,
                                            @FocusLoginUser Long uid) {
        if (!dto.getUid().equals(uid)) return FocusResult.error("只能修改自己的信息");
        userService.updateUserInfo(dto);
        return FocusResult.success();
    }
}
