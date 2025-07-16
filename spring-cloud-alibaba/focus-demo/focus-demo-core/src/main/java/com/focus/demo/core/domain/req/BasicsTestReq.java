package com.focus.demo.core.domain.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 基础测试表
 */
@Data
public class BasicsTestReq {
    /**
     * 测试名称
     */
    @NotBlank(message = "测试名称不能为空")
    private String name;
}
