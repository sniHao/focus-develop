package com.focus.oauth.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zi-wei
 * @create 2025/5/23 11:11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/focusOauth")
public class UserController {

    @GetMapping("/test")
    public String dealReport() {
        return "focus-oauth";
    }
}
