package com.focus.basedata.core.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.focus.basedata.core.domain.entity.BaseData;
import com.focus.basedata.core.repository.mapper.BaseDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础数据仓储
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Service
public class BaseDataRepository extends ServiceImpl<BaseDataMapper, BaseData> {
    
    /**
     * 根据类型查找基础数据
     * 
     * @param type 数据类型
     * @return 基础数据列表
     */
    public List<BaseData> findByType(String type) {
        QueryWrapper<BaseData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.eq("status", 1);
        queryWrapper.orderByAsc("sort");
        return this.list(queryWrapper);
    }
} 