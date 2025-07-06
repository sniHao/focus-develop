package com.focus.api.controller;

import com.focus.api.common.util.FocusResult;
import com.focus.application.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin-Controller", description = "管理员接口")
public class AdminController {

    private final AdminService adminService;

    //    @FocusRateLimiter(time = 10, count = 1, limitType = LimiterType.IP) 限流注解需要同时启动redis
    @GetMapping("/sayHello")
    public FocusResult<?> sayHello() {
        return FocusResult.success(adminService.sayHello());
    }

}
