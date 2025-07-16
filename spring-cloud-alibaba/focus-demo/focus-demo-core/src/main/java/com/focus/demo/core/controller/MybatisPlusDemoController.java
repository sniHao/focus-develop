package com.focus.demo.core.controller;

import com.focus.demo.core.application.BasicsTestApplicationService;
import com.focus.demo.core.domain.vo.BasicsTestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zi-wei
 * @create 2025/6/6 10:35
 */
@RestController
@RequestMapping("/demo/mybatisPlus")
@RequiredArgsConstructor
public class MybatisPlusDemoController {
    private final BasicsTestApplicationService basicsTestApplicationService;

    /**
     * 列表查询
     *
     * @return
     */
    @GetMapping("/queryList")
    public List<BasicsTestVo> queryList() {
        return basicsTestApplicationService.queryList();
    }
}
