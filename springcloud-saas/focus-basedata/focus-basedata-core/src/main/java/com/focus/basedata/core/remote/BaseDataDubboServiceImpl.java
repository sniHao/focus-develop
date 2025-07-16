package com.focus.basedata.core.remote;

import cn.hutool.core.bean.BeanUtil;
import com.focus.oauth.api.dto.BaseDataDTO;
import com.focus.oauth.api.dto.UserInfoDTO;
import com.focus.oauth.api.dubbo.BaseDataDubboService;
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
public class BaseDataDubboServiceImpl implements BaseDataDubboService {

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
        userInfo.setPhone("138****" + String.format("%04d", userId % 10000));
        userInfo.setGender(userId % 2 == 0 ? 1 : 0);
        userInfo.setStatus(1);
        userInfo.setCreateTime(LocalDateTime.now().minusDays(userId % 30));
        userInfo.setUpdateTime(LocalDateTime.now());
        userInfo.setLastLoginTime(LocalDateTime.now().minusHours(userId % 24));

        return userInfo;
    }

    @Override
    public List<BaseDataDTO> getAllBaseData() {
        log.info("远程调用获取所有基础数据");

        List<BaseDataVo> voList = baseDataDomainService.getAllBaseData();
        return BeanUtil.copyToList(voList, BaseDataDTO.class);
    }

    @Override
    public List<BaseDataDTO> getBaseDataByType(String type) {
        log.info("远程调用根据类型获取基础数据，type: {}", type);

        List<BaseDataVo> voList = baseDataDomainService.getBaseDataByType(type);
        return BeanUtil.copyToList(voList, BaseDataDTO.class);
    }

    @Override
    public boolean checkUserExists(Long userId) {
        log.info("远程调用检查用户是否存在，userId: {}", userId);

        // 模拟用户存在性检查
        return userId != null && userId > 0 && userId <= 1000;
    }

    @Override
    public String getSystemConfig(String configKey) {
        log.info("远程调用获取系统配置，configKey: {}", configKey);

        // 模拟系统配置获取
        switch (configKey) {
            case "system.name":
                return "Focus管理系统";
            case "system.version":
                return "1.0.0";
            case "system.author":
                return "Focus Team";
            case "system.environment":
                return "development";
            case "system.timezone":
                return "Asia/Shanghai";
            default:
                return "未知配置项: " + configKey;
        }
    }
}
