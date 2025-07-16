package com.focus.basedata.core.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.focus.basedata.core.domain.entity.BaseData;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基础数据Mapper
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Mapper
public interface BaseDataMapper extends BaseMapper<BaseData> {
    
} 