package com.focus.oauth.api.service;

import com.focus.oauth.api.dto.BaseDataDTO;
import com.focus.oauth.api.dto.UserInfoDTO;
import com.focus.oauth.api.dubbo.BaseDataDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础数据远程调用消费者服务
 *
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@Service
public class BaseDataConsumerService {

    @DubboReference(version = "1.0.0", group = "basedata")
    private BaseDataDubboService baseDataDubboService;

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public UserInfoDTO getUserInfo(Long userId) {
        log.info("调用基础数据服务获取用户信息，userId: {}", userId);
        try {
            return baseDataDubboService.getUserInfo(userId);
        } catch (Exception e) {
            log.error("调用基础数据服务获取用户信息失败，userId: {}", userId, e);
            return null;
        }
    }

    /**
     * 获取所有基础数据
     *
     * @return 基础数据列表
     */
    public List<BaseDataDTO> getAllBaseData() {
        log.info("调用基础数据服务获取所有基础数据");
        try {
            return baseDataDubboService.getAllBaseData();
        } catch (Exception e) {
            log.error("调用基础数据服务获取所有基础数据失败", e);
            return null;
        }
    }

    /**
     * 根据类型获取基础数据
     *
     * @param type 数据类型
     * @return 基础数据列表
     */
    public List<BaseDataDTO> getBaseDataByType(String type) {
        log.info("调用基础数据服务根据类型获取基础数据，type: {}", type);
        try {
            return baseDataDubboService.getBaseDataByType(type);
        } catch (Exception e) {
            log.error("调用基础数据服务根据类型获取基础数据失败，type: {}", type, e);
            return null;
        }
    }

    /**
     * 检查用户是否存在
     *
     * @param userId 用户ID
     * @return 是否存在
     */
    public boolean checkUserExists(Long userId) {
        log.info("调用基础数据服务检查用户是否存在，userId: {}", userId);
        try {
            return baseDataDubboService.checkUserExists(userId);
        } catch (Exception e) {
            log.error("调用基础数据服务检查用户是否存在失败，userId: {}", userId, e);
            return false;
        }
    }

    /**
     * 获取系统配置
     *
     * @param configKey 配置key
     * @return 配置值
     */
    public String getSystemConfig(String configKey) {
        log.info("调用基础数据服务获取系统配置，configKey: {}", configKey);
        try {
            return baseDataDubboService.getSystemConfig(configKey);
        } catch (Exception e) {
            log.error("调用基础数据服务获取系统配置失败，configKey: {}", configKey, e);
            return null;
        }
    }
}
