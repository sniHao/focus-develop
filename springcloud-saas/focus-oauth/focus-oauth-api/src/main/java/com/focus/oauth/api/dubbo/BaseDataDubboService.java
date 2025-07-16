package com.focus.oauth.api.dubbo;

import com.focus.oauth.api.dto.BaseDataDTO;
import com.focus.oauth.api.dto.UserInfoDTO;

import java.util.List;

/**
 * 基础数据远程服务接口
 *
 * @author zi-wei
 * @create 2025/1/8
 */
public interface BaseDataDubboService {

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfoDTO getUserInfo(Long userId);

    /**
     * 获取所有基础数据
     *
     * @return 基础数据列表
     */
    List<BaseDataDTO> getAllBaseData();

    /**
     * 根据类型获取基础数据
     *
     * @param type 数据类型
     * @return 基础数据列表
     */
    List<BaseDataDTO> getBaseDataByType(String type);

    /**
     * 验证用户是否存在
     *
     * @param userId 用户ID
     * @return 是否存在
     */
    boolean checkUserExists(Long userId);

    /**
     * 获取系统配置
     *
     * @param configKey 配置key
     * @return 配置值
     */
    String getSystemConfig(String configKey);
}
