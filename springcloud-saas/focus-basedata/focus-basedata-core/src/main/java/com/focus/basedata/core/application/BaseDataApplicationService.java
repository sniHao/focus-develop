package com.focus.basedata.core.application;

import com.focus.basedata.core.domain.req.BaseDataReq;
import com.focus.basedata.core.domain.vo.BaseDataVo;
import com.focus.basedata.core.domainservice.BaseDataDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础数据应用服务
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseDataApplicationService {
    
    private final BaseDataDomainService baseDataDomainService;
    
    /**
     * 获取所有基础数据
     * 
     * @return 基础数据列表
     */
    public List<BaseDataVo> getAllBaseData() {
        log.info("获取所有基础数据");
        return baseDataDomainService.getAllBaseData();
    }
    
    /**
     * 根据类型获取基础数据
     * 
     * @param type 数据类型
     * @return 基础数据列表
     */
    public List<BaseDataVo> getBaseDataByType(String type) {
        log.info("根据类型获取基础数据，type: {}", type);
        return baseDataDomainService.getBaseDataByType(type);
    }
    
    /**
     * 创建基础数据
     * 
     * @param req 基础数据请求
     * @return 创建结果
     */
    public String createBaseData(BaseDataReq req) {
        log.info("创建基础数据，req: {}", req);
        return baseDataDomainService.createBaseData(req);
    }
    
    /**
     * 更新基础数据
     * 
     * @param id 数据ID
     * @param req 基础数据请求
     * @return 更新结果
     */
    public String updateBaseData(Long id, BaseDataReq req) {
        log.info("更新基础数据，id: {}, req: {}", id, req);
        return baseDataDomainService.updateBaseData(id, req);
    }
    
    /**
     * 删除基础数据
     * 
     * @param id 数据ID
     * @return 删除结果
     */
    public String deleteBaseData(Long id) {
        log.info("删除基础数据，id: {}", id);
        return baseDataDomainService.deleteBaseData(id);
    }
} 