# Focus Spring-cloud-alibaba 微服务架构

> 基于 Spring Cloud Alibaba+ Dubbo 的企业级微服务架构项目

## 项目概述

Focus Spring-cloud-alibaba 是一个采用微服务架构的企业级应用平台，整合了 Spring Cloud 生态和 Apache Dubbo RPC 框架，提供完整的服务治理、网关路由、认证授权等功能。

## 架构设计

### 技术栈
- **服务框架**: Spring Boot 3.x + Spring Cloud 2023.x
- **RPC框架**: Apache Dubbo 3.x
- **注册中心**: Nacos
- **网关**: Spring Cloud Gateway
- **数据库**: MySQL + MyBatis Plus
- **搜索引擎**: Elasticsearch
- **构建工具**: Maven

### 服务通信方式
- **HTTP**: 用于对外API服务（通过网关）
- **Dubbo RPC**: 用于内部服务间通信

## 模块架构

```
Spring-cloud-alibaba/
├── focus-dependencies/           # 依赖管理
├── focus-framework/             # 框架核心模块
│   ├── focus-core/             # 核心组件
│   ├── focus-web/              # Web组件
│   ├── focus-mybatis-plus/     # MyBatis Plus组件
│   └── focus-elasticsearch/    # Elasticsearch组件
├── focus-gateway/              # API网关服务
├── focus-oauth/                # OAuth认证服务
├── focus-basedata/             # 基础数据服务
└── focus-demo/                 # 演示模块
```

## 详细模块说明
### 3. focus-gateway
**API网关服务**
- **端口**: 10010
- **功能**: 
  - 统一入口管理
  - 路由转发
  - 负载均衡
  - 请求过滤

### 4. focus-oauth
**OAuth认证服务**
- **HTTP端口**: 10012
- **Dubbo端口**: 20880
- **模块结构**:
  ```
  focus-oauth/
  ├── focus-oauth-api/           # API定义层
  ├── focus-oauth-core/          # 核心业务层
  └── focus-oauth-startup/       # 启动模块
  ```

### 5. focus-basedata
**基础数据服务**
- **HTTP端口**: 10014
- **Dubbo端口**: 20881
- **模块结构**:
  ```
  focus-basedata/
  ├── focus-basedata-api/        # API定义层
  ├── focus-basedata-core/       # 核心业务层
  └── focus-basedata-startup/    # 启动模块
  ```

### 6. focus-demo
**演示模块**
- 技术特性演示
- API使用示例
- 集成测试案例

## 服务通信架构

### HTTP通信 (通过网关)
```
客户端 → focus-gateway:10010 → focus-oauth:10012
客户端 → focus-gateway:10010 → focus-basedata:10014
```

### Dubbo RPC通信 (内部服务)
```
focus-oauth ←→ focus-basedata (Dubbo RPC)
```

### Nacos注册中心
- **HTTP服务**: 注册到 `public` 命名空间
- **Dubbo服务**: 注册到 `dubbo-group` 命名空间

## 设计原则

### 1. 依赖倒置原则
- **调用方定义接口，被调用方实现接口**
- focus-oauth-api 定义服务接口
- focus-basedata-core 实现服务接口

### 2. 模块职责分离
- **API层**: 接口定义、DTO定义
- **Core层**: 业务逻辑实现
- **Startup层**: 启动配置、环境配置

### 3. 避免循环依赖
```
focus-oauth-core → focus-oauth-api
focus-basedata-core → focus-oauth-api
```

## 开发规范

### 1. 模块命名规范
- **framework模块**: focus-{组件名}
- **业务模块**: focus-{业务域}
- **子模块**: {父模块}-{api|core|startup}

### 2. 包结构规范
```
com.focus.{模块名}.{层次}
├── api/           # API定义
├── core/          # 核心业务
│   ├── domain/    # 领域模型
│   ├── application/ # 应用服务
│   ├── controller/  # 控制器
│   └── remote/    # 远程服务
└── startup/       # 启动配置
```
