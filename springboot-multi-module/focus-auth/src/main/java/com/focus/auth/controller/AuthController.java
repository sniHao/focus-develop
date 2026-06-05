package com.focus.auth.controller;

import com.focus.auth.service.AuthService;
import com.focus.model.dto.LoginDTO;
import com.focus.common.result.FocusResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 认证控制器
 * 登录、登出
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证接口", description = "登录、登出")
public class AuthController {

    private final AuthService authService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public FocusResult<Map<String, Object>> login(@Valid @RequestBody LoginDTO dto) {
        return FocusResult.success(authService.login(dto));
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public FocusResult<Void> logout() {
        authService.logout();
        return FocusResult.success();
    }
}
