<h1 align="center">👋focus-develop <专注开发></h1>

Focus-Develop
是一套多语言、多场景覆盖的开源项目集合，旨在为开发者提供"开箱即用"的项目框架与工具，帮助用户跳过繁琐的基础搭建环节，快速进入核心业务逻辑开发。其核心设计理念是极简主义与模块化，通过分层架构、标准化协议和灵活的扩展机制，适配云端、移动端、微服务等主流开发场景，同时兼容多种编程语言和工具链。

***"Focus-Develop 不仅是工具集合，更是开发理念的实践——让技术回归业务本质。"***

**开源地址**：[GitHub](https://github.com/sniHao/focus-develop) &nbsp;&nbsp;&nbsp;[Gitee](https://gitee.com/snihao/focus-develop)

# 🥪springboot-multi-module

## 🥘项目介绍

基于 Spring Boot 3.x 构建的多模块架构脚手架，采用经典三层架构（Controller → Service → Mapper），集成 MyBatis-Plus 增强、Sa-Token 权限认证、Druid 连接池、MapStruct 对象转换、SpringDoc API 文档等企业级开发套件。通过模块化分层实现数据与行为分离，提供手机号验证码登录、`@FocusLoginUser` 参数注入、统一响应封装、全局异常处理等开箱即用能力，助力快速搭建后台管理系统。

## 🥢技术栈

| 框架/工具 | 说明 | 版本 | 指南 |
|-----------|------|------|------|
| [Spring Boot](https://spring.io/projects/spring-boot) | 企业级开发框架 | 3.5.3 | [文档](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) |
| [MyBatis-Plus](https://baomidou.com/) | MyBatis 增强工具 | 3.5.7 | [文档](https://baomidou.com/pages/24112f/) |
| [MySQL](https://www.mysql.com/) | 关系型数据库 | 8.0+ | [文档](https://dev.mysql.com/doc/) |
| [Redis](https://redis.io/)（需yml中配置） | 缓存数据库 | 6.0+ | [文档](https://redis.io/docs/) |
| [Sa-Token](https://sa-token.cc/) | 权限认证框架 | 1.44.0 | [文档](https://sa-token.cc/doc.html) |
| [Druid](https://github.com/alibaba/druid) | 数据库连接池 | 1.2.23 | [文档](https://github.com/alibaba/druid/wiki) |
| [MapStruct](https://mapstruct.org/) | 对象转换框架 | 1.5.5 | [文档](https://mapstruct.org/documentation/) |
| [Hutool](https://hutool.cn/) | Java 工具集 | 5.8.24 | [文档](https://hutool.cn/docs/) |
| [SpringDoc OpenAPI](https://springdoc.org/) | API 文档生成 | 2.8.5 | [文档](https://springdoc.org/) |

## ☕运行配置

建议开发环境保持一致以避免兼容性问题

| 依赖 | 版本要求 |
|------|----------|
| JDK | 17+ |
| Maven | 3.8+ |
| MySQL | 8.0+ |
| Redis | 6.0+ |

## 🚴快速开始

### 🏀克隆项目

注：git版本需要大于2.25以上才能使用以下命令(稀疏检出)，若小于此版本可以选择clone全部文件，手动选出所需要的项目文件。

```bash
git clone --filter=blob:none --sparse https://gitee.com/snihao/focus-develop.git .
git sparse-checkout set springboot-multi-module
```

### 🍜创建数据库

```sql
CREATE DATABASE IF NOT EXISTS `focus` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

### 🍲创建用户表

```sql
CREATE TABLE `u_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT '用户ID（业务ID）',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `gender` int DEFAULT NULL COMMENT '性别（0：女  1：男）',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `deleted` int DEFAULT 0 COMMENT '逻辑删除（0-未删除 1-已删除）',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新数据时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid` (`uid`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
```

### 🍵修改配置

编辑 `focus-boot/src/main/resources/application.yml`，修改数据库和Redis连接信息。

### 🍶启动项目

```bash
mvn clean install
cd focus-boot
mvn spring-boot:run
```

### 🧃访问API文档

启动后访问：http://localhost:1955/swagger-ui.html

## 🧩模块结构

```
springboot-multi-module
├── focus-model         # 数据模型模块（Entity、Mapper、DTO、VO）
├── focus-common        # 公共能力模块（统一响应、异常、常量、工具类、拦截器、配置）
├── focus-auth          # 认证鉴权模块（手机号验证码登录、@FocusLoginUser注解、Sa-Token集成）
├── focus-user          # 用户业务模块（用户信息查询和更新）
└── focus-boot          # 启动入口模块（配置、启动类）
```

### 模块依赖关系

```
focus-model ← focus-common ← focus-auth ← focus-user
                                  ↑              ↓
                                  └──────────────┘
                                         +
                                   focus-common
                                         ↓
                                      focus-boot
```

### 各模块职责

| 模块 | 职责 | 子包 |
|------|------|------|
| **focus-model** | 纯数据定义，无Spring依赖 | `entity/` 实体、`mapper/` Mapper、`dto/` 请求DTO、`vo/` 响应VO |
| **focus-common** | 运行时公共能力 | `result/` 统一响应、`exception/` 异常、`constant/` 常量、`util/` 工具类、`config/` 配置、`interceptor/` 拦截器、`annotation/` 注解、`handler/` 异常处理 |
| **focus-auth** | 手机号验证码登录、`@FocusLoginUser`参数注入 | `annotation/` 注解、`resolver/` 参数解析器、`config/` 配置、`controller/` 认证接口、`service/` 认证业务 |
| **focus-user** | 用户信息管理 | `controller/` 接口层、`service/` 业务层、`convert/` 对象转换 |
| **focus-boot** | 启动入口 | — |

## 📡API接口

### 认证接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/auth/login` | 手机号验证码登录（返回token） |
| POST | `/auth/logout` | 用户登出 |

### 用户接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/user/info` | 获取当前登录用户信息（需登录） |
| PUT | `/user/update` | 更新用户信息（需登录） |

## 🔥接口示例

### 1. 登录（手机号验证码）

```bash
curl -X POST http://localhost:1955/auth/login \
  -H "Content-Type: application/json" \
  -d '{"phone": "13800138000", "code": "123456"}'
```

响应：

```json
{
  "code": "200",
  "msg": "ok",
  "data": {
    "token": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "uid": 1717584000000,
    "phone": "13800138000",
    "name": "用户8000"
  }
}
```

**说明**：首次登录自动注册，验证码固定为 `123456`（实际项目中应通过短信发送）。

### 2. 获取用户信息

登录后，请求头携带 `focus-sa-token: <token>`：

```bash
curl http://localhost:1955/user/info \
  -H "focus-sa-token: a1b2c3d4-e5f6-7890-abcd-ef1234567890"
```

响应：

```json
{
  "code": "200",
  "msg": "ok",
  "data": {
    "uid": 1717584000000,
    "phone": "13800138000",
    "name": "用户8000",
    "photo": null,
    "gender": null,
    "address": null,
    "createDate": "2026-06-05 12:00:00"
  }
}
```

### 3. 更新用户信息

```bash
curl -X PUT http://localhost:1955/user/update \
  -H "Content-Type: application/json" \
  -H "focus-sa-token: a1b2c3d4-e5f6-7890-abcd-ef1234567890" \
  -d '{
    "uid": 1717584000000,
    "name": "张三",
    "gender": 1,
    "address": "北京市朝阳区"
  }'
```

响应：

```json
{
  "code": "200",
  "msg": "ok",
  "data": null
}
```

## 💡核心用法

### @FocusLoginUser 获取当前登录用户

在Controller方法参数上标注 `@FocusLoginUser Long uid`，自动注入当前登录用户ID：

```java
@GetMapping("/info")
public FocusResult<UserVO> getUserInfo(@FocusLoginUser Long uid) {
    // uid = 当前登录用户ID，未登录时自动拦截
    return FocusResult.success(userService.getUserInfo(uid));
}
```

### 扩展新业务模块

以 `focus-user` 为参考，新增业务模块只需 4 步：

**1. 创建模块目录**

```
focus-xxx/
  └── src/main/java/com/focus/xxx/
      ├── controller/
      ├── service/
      │   └── impl/
      └── convert/
```

**2. 编写 pom.xml**

继承 `focus-parent`，依赖 `focus-common` 和 `focus-auth`。

**3. 在根 pom.xml 注册模块和依赖管理**

**4. 在 focus-boot 的 pom.xml 中引入**

## 🏗️架构设计原则

- **数据与行为分离**：focus-model 纯数据定义，focus-common 运行时能力
- **分层清晰**：Controller → Service → Mapper，单向依赖无环
- **契约优先**：DTO/VO 在 model 模块统一管理，模块间通过契约通信
- **开箱即用**：内置手机号验证码登录（Sa-Token）、签名防重放、分布式限流、全局异常处理、API文档

## 🤝加入社区

**推荐贡献路径**：

1. 提交 Issue 报告问题或建议
2. 认领开发任务
3. 提交 PR（遵循 Git 规范）
4. 成为核心维护者 🔥

## 📄License

[GPL-2.0](../../LICENSE)
