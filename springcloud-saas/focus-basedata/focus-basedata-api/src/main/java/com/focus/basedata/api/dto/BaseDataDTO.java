package com.focus.basedata.api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础数据DTO
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDataDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 数据类型
     */
    private String type;
    
    /**
     * 数据编码
     */
    private String code;
    
    /**
     * 数据名称
     */
    private String name;
    
    /**
     * 数据值
     */
    private String value;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 