package com.focus.basedata.core.domain.vo;

import com.focus.basedata.core.domain.entity.BaseData;
import lombok.Data;

/**
 * 基础数据视图对象
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Data
public class BaseDataVo extends BaseData {
    
    /**
     * 类型名称
     */
    private String typeName;
    
    /**
     * 状态名称
     */
    private String statusName;
} 