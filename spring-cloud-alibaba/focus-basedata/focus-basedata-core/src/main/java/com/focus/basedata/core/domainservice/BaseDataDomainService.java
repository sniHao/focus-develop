package com.focus.basedata.core.domainservice;

import cn.hutool.core.bean.BeanUtil;
import com.focus.basedata.core.domain.entity.BaseData;
import com.focus.basedata.core.domain.req.BaseDataReq;
import com.focus.basedata.core.domain.vo.BaseDataVo;
import com.focus.basedata.core.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础数据领域服务
 * 
 * @author zi-wei
 * @create 2025/1/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseDataDomainService {
    
    private final BaseDataRepository baseDataRepository;
    
    /**
     * 获取所有基础数据
     * 
     * @return 基础数据列表
     */
    public List<BaseDataVo> getAllBaseData() {
        List<BaseData> dataList = baseDataRepository.list();
        return BeanUtil.copyToList(dataList, BaseDataVo.class);
    }
    
    /**
     * 根据类型获取基础数据
     * 
     * @param type 数据类型
     * @return 基础数据列表
     */
    public List<BaseDataVo> getBaseDataByType(String type) {
        List<BaseData> dataList = baseDataRepository.findByType(type);
        return BeanUtil.copyToList(dataList, BaseDataVo.class);
    }
    
    /**
     * 创建基础数据
     * 
     * @param req 基础数据请求
     * @return 创建结果
     */
    public String createBaseData(BaseDataReq req) {
        BaseData baseData = BeanUtil.copyProperties(req, BaseData.class);
        boolean saved = baseDataRepository.save(baseData);
        return saved ? "创建成功" : "创建失败";
    }
    
    /**
     * 更新基础数据
     * 
     * @param id 数据ID
     * @param req 基础数据请求
     * @return 更新结果
     */
    public String updateBaseData(Long id, BaseDataReq req) {
        BaseData baseData = baseDataRepository.getById(id);
        if (baseData == null) {
            return "数据不存在";
        }
        
        BeanUtil.copyProperties(req, baseData);
        boolean updated = baseDataRepository.updateById(baseData);
        return updated ? "更新成功" : "更新失败";
    }
    
    /**
     * 删除基础数据
     * 
     * @param id 数据ID
     * @return 删除结果
     */
    public String deleteBaseData(Long id) {
        boolean removed = baseDataRepository.removeById(id);
        return removed ? "删除成功" : "删除失败";
    }
} 