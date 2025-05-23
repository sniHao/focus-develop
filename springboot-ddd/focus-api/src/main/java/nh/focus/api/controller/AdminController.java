package nh.focus.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nh.focus.application.service.AdminService;
import nh.focus.common.limiter.LimiterType;
import nh.focus.common.limiter.RateLimiter;
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

    @RateLimiter(time = 10, count = 1, limitType = LimiterType.IP)
    @GetMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }

}
