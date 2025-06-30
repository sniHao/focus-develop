package com.focus.api.controller;


import com.focus.application.service.AdminService;
import com.focus.common.limiter.FocusRateLimiter;
import com.focus.common.limiter.LimiterType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin-Controller", description = "管理员接口")
public class AdminController {

    private final AdminService adminService;

    //    @FocusRateLimiter(time = 10, count = 1, limitType = LimiterType.IP) 限流注解需要同时启动redis
    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }

}
