package com.focus.framework.mybatis.autoconfigure;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mybatis 配置
 */
@Data
public class FocusMybatisProperties {

    /**
     * 应用名称
     */
    private String applicationName = "";
    /**
     * 是否开启慢 SQL 检测
     */
    private boolean slowSqlEnabled = true;

    /**
     * 是否开启操作日志
     */
    private boolean operSqlEnabled = true;
    /**
     * 允许操作的命令类型
     */
    private List<String> allowOperType = List.of("INSERT", "UPDATE", "DELETE");
    /**
     * 慢 SQL 检测的阈值时间（毫秒）
     */
    private long slowSqlThreshold = 1000; // 可根据实际情况调整慢速 SQL 的阈值时间（毫秒）

    public Map<String, String> getAllowOperTypeMap() {
        return allowOperType.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
    }

}
