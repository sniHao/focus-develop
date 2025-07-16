package com.focus.basedata.core.controller;

import com.focus.basedata.core.application.BaseDataApplicationService;
import com.focus.basedata.core.domain.req.BaseDataReq;
import com.focus.basedata.core.domain.vo.BaseDataVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础数据控制器 - Web业务
 *
 * @author zi-wei
 * @create 2025/1/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/focus-basedata")
public class BaseDataController {

    private final BaseDataApplicationService baseDataApplicationService;

    /**
     * 获取所有基础数据
     *
     * @return 基础数据列表
     */
    @GetMapping("/all")
    public List<BaseDataVo> getAllBaseData() {
        return baseDataApplicationService.getAllBaseData();
    }

    /**
     * 根据类型获取基础数据
     *
     * @param type 数据类型
     * @return 基础数据列表
     */
    @GetMapping("/type/{type}")
    public List<BaseDataVo> getBaseDataByType(@PathVariable String type) {
        return baseDataApplicationService.getBaseDataByType(type);
    }

    /**
     * 创建基础数据
     *
     * @param req 基础数据请求
     * @return 创建结果
     */
    @PostMapping
    public String createBaseData(@RequestBody BaseDataReq req) {
        return baseDataApplicationService.createBaseData(req);
    }

    /**
     * 更新基础数据
     *
     * @param id 数据ID
     * @param req 基础数据请求
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public String updateBaseData(@PathVariable Long id, @RequestBody BaseDataReq req) {
        return baseDataApplicationService.updateBaseData(id, req);
    }

    /**
     * 删除基础数据
     *
     * @param id 数据ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public String deleteBaseData(@PathVariable Long id) {
        return baseDataApplicationService.deleteBaseData(id);
    }

    /**
     * 健康检查
     *
     * @return 结果
     */
    @GetMapping("/health")
    public String health() {
        return "基础数据服务运行正常";
    }
}
