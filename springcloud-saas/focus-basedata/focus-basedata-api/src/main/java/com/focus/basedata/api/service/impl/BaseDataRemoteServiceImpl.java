package com.focus.basedata.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.focus.basedata.api.dto.BaseDataDTO;
import com.focus.basedata.api.dto.UserInfoDTO;
import com.focus.basedata.api.service.BaseDataRemoteService;
import com.focus.basedata.core.domain.vo.BaseDataVo;
import com.focus.basedata.core.domainservice.BaseDataDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 基础数据远程服务实现类
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@DubboService(version = "1.0.0", group = "basedata")
@Service
@RequiredArgsConstructor
public class BaseDataRemoteServiceImpl implements BaseDataRemoteService {
    
    private final BaseDataDomainService baseDataDomainService;
    
    @Override
    public UserInfoDTO getUserInfo(Long userId) {
        log.info("远程调用获取用户信息，userId: {}", userId);
        
        // 模拟从数据库获取用户信息
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserId(userId);
        userInfo.setUsername("user_" + userId);
        userInfo.setNickname("用户" + userId);
        userInfo.setEmail("user" + userId + "@example.com");
        userInfo.setPhone("1388888" + String.format("%04d", userId));
        userInfo.setAvatar("https://avatar.example.com/" + userId);
        userInfo.setGender(userId % 2 == 0 ? 1 : 0);
        userInfo.setStatus(1);
        userInfo.setLastLoginTime(LocalDateTime.now().minusHours(1));
        userInfo.setCreateTime(LocalDateTime.now().minusDays(30));
        userInfo.setUpdateTime(LocalDateTime.now());
        
        return userInfo;
    }
    
    @Override
    public List<BaseDataDTO> getAllBaseData() {
        log.info("远程调用获取所有基础数据");
        
        List<BaseDataVo> dataList = baseDataDomainService.getAllBaseData();
        return BeanUtil.copyToList(dataList, BaseDataDTO.class);
    }
    
    @Override
    public List<BaseDataDTO> getBaseDataByType(String type) {
        log.info("远程调用根据类型获取基础数据，type: {}", type);
        
        List<BaseDataVo> dataList = baseDataDomainService.getBaseDataByType(type);
        return BeanUtil.copyToList(dataList, BaseDataDTO.class);
    }
    
    @Override
    public boolean checkUserExists(Long userId) {
        log.info("远程调用检查用户是否存在，userId: {}", userId);
        
        // 模拟检查用户是否存在
        return userId != null && userId > 0 && userId <= 1000;
    }
    
    @Override
    public String getSystemConfig(String configKey) {
        log.info("远程调用获取系统配置，configKey: {}", configKey);
        
        // 从基础数据表中获取系统配置
        List<BaseDataVo> configList = baseDataDomainService.getBaseDataByType("SYSTEM_CONFIG");
        for (BaseDataVo config : configList) {
            if (configKey.equals(config.getCode())) {
                return config.getValue();
            }
        }
        
        // 默认配置
        return switch (configKey) {
            case "SYSTEM_NAME" -> "Focus SaaS Platform";
            case "SYSTEM_VERSION" -> "1.0.0";
            case "SYSTEM_AUTHOR" -> "zi-wei";
            case "SYSTEM_DESCRIPTION" -> "基于Spring Cloud Alibaba的微服务SaaS平台";
            default -> "未知配置";
        };
    }
} 