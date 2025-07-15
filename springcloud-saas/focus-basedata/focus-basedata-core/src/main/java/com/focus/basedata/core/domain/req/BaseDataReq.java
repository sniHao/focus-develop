package com.focus.basedata.core.domain.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 基础数据请求
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Data
public class BaseDataReq {
    
    /**
     * 数据类型
     */
    @NotBlank(message = "数据类型不能为空")
    private String type;
    
    /**
     * 数据编码
     */
    @NotBlank(message = "数据编码不能为空")
    private String code;
    
    /**
     * 数据名称
     */
    @NotBlank(message = "数据名称不能为空")
    private String name;
    
    /**
     * 数据值
     */
    @NotBlank(message = "数据值不能为空")
    private String value;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;
    
    /**
     * 状态（0：禁用，1：启用）
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
} 