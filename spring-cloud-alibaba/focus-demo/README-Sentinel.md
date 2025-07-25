# Sentinel 集成说明文档

## 1. 简介

本项目已集成阿里巴巴的 Sentinel 分布式系统的流量防卫兵，用于实现熔断降级、流量控制、系统负载保护等功能。

## 2. 配置说明

### 2.1 依赖配置

已在以下模块添加 Sentinel 依赖：

- `focus-demo-startup`：添加了 `spring-cloud-starter-alibaba-sentinel` 依赖
- `focus-demo-core`：添加了 `spring-cloud-starter-alibaba-sentinel` 依赖

### 2.2 Sentinel 控制台配置

在 `focus-demo-startup` 模块的 `application.yml` 中配置了 Sentinel 控制台地址：

```yaml
spring:
  cloud:
    sentinel:
      transport:
        dashboard: 127.0.0.1:8333  # Sentinel 控制台地址
        port: 8719                 # 应用与Sentinel控制台通信端口
      eager: true                  # 取消控制台懒加载，即项目启动即连接控制台
```

## 3. 测试接口说明

在 `focus-demo-core` 模块中创建了 `SentinelController`，提供了以下测试接口：

### 3.1 流量控制测试

- 接口路径：`/focus-demo/demo/sentinel/flowControl`
- 请求方式：GET
- 请求参数：`name`（可选）
- 资源名称：`flowControl`
- 降级方法：`flowControlBlockHandler`

### 3.2 熔断降级测试

- 接口路径：`/focus-demo/demo/sentinel/circuitBreaking`
- 请求方式：GET
- 请求参数：`number`（可选，传入0会触发异常）
- 资源名称：`circuitBreaking`
- 降级方法：`circuitBreakingFallback`

### 3.3 热点参数限流测试

- 接口路径：`/focus-demo/demo/sentinel/hotParamFlow`
- 请求方式：GET
- 请求参数：`productId`、`userId`（可选）
- 资源名称：`hotParamFlow`
- 降级方法：`hotParamFlowBlockHandler`

### 3.4 系统自适应保护测试

- 接口路径：`/focus-demo/demo/sentinel/systemProtection`
- 请求方式：GET
- 资源名称：`systemProtection`
- 降级方法：`systemProtectionBlockHandler`

## 4. 如何在 Sentinel 控制台配置规则

### 4.1 流控规则配置

1. 启动应用后，访问 Sentinel 控制台（http://127.0.0.1:8333）
2. 在左侧菜单选择「流控规则」
3. 点击「新增流控规则」按钮
4. 填写规则信息：
   - 资源名：`flowControl`
   - 针对来源：`default`
   - 阈值类型：QPS
   - 单机阈值：1（表示每秒最多处理1个请求）
   - 流控模式：直接
   - 流控效果：快速失败
5. 点击「新增」按钮保存规则

### 4.2 熔断规则配置

1. 在左侧菜单选择「熔断规则」
2. 点击「新增熔断规则」按钮
3. 填写规则信息：
   - 资源名：`circuitBreaking`
   - 熔断策略：异常比例
   - 最小请求数：5
   - 比例阈值：0.6（表示异常比例超过60%触发熔断）
   - 熔断时长：10（表示熔断后10秒内自动恢复）
4. 点击「新增」按钮保存规则

### 4.3 热点规则配置

1. 在左侧菜单选择「热点规则」
2. 点击「新增热点规则」按钮
3. 填写规则信息：
   - 资源名：`hotParamFlow`
   - 参数索引：0（表示第一个参数，即productId）
   - 单机阈值：2（表示每秒最多处理2个请求）
   - 统计窗口时长：2
4. 点击「新增」按钮保存规则

### 4.4 系统规则配置

1. 在左侧菜单选择「系统规则」
2. 点击「新增系统规则」按钮
3. 填写规则信息：
   - 系统保护类型：LOAD（系统负载）
   - 阈值：0.8（当系统负载超过0.8时触发保护）
4. 点击「新增」按钮保存规则

## 5. 测试方法

1. 启动应用
2. 在 Sentinel 控制台配置相应规则
3. 使用浏览器或 Postman 等工具访问测试接口
4. 观察接口返回结果和 Sentinel 控制台监控数据

例如，要测试流控功能，可以快速多次访问：
```
http://localhost:10010/focus-demo/demo/sentinel/flowControl?name=test
```

当QPS超过设定阈值时，将触发流控并返回降级信息。