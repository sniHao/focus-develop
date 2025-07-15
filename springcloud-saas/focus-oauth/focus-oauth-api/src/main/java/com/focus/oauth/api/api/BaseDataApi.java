package com.focus.oauth.api.api;

import com.focus.basedata.api.dto.BaseDataDTO;
import com.focus.oauth.core.service.BaseDataConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础数据远程调用API
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth/basedata")
public class BaseDataApi {
    
    private final BaseDataConsumerService baseDataConsumerService;
    
    /**
     * 通过远程调用获取所有基础数据
     * 
     * @return 基础数据列表
     */
    @GetMapping("/all")
    public List<BaseDataDTO> getAllBaseData() {
        return baseDataConsumerService.getAllBaseData();
    }
    
    /**
     * 通过远程调用根据类型获取基础数据
     * 
     * @param type 数据类型
     * @return 基础数据列表
     */
    @GetMapping("/type/{type}")
    public List<BaseDataDTO> getBaseDataByType(@PathVariable String type) {
        return baseDataConsumerService.getBaseDataByType(type);
    }
} 