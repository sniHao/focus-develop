<h1 align="center">👋focus-develop <专注开发></h1>

Focus-Develop
是一套多语言、多场景覆盖的开源项目集合，旨在为开发者提供“开箱即用”的项目框架与工具，帮助用户跳过繁琐的基础搭建环节，快速进入核心业务逻辑开发。其核心设计理念是​​极简主义​​与​​模块化​​，通过分层架构、标准化协议和灵活的扩展机制，适配云端、移动端、微服务等主流开发场景，同时兼容多种编程语言和工具链。

***"Focus-Develop 不仅是工具集合，更是开发理念的实践——让技术回归业务本质。"***

**开源地址**：[GitHub](https://gitee.com/snihao/focus-develop) &nbsp;&nbsp;&nbsp;[Gitee](https://gitee.com/snihao/focus-develop)

# 🥪springboot-ddd

## 🥘项目介绍

基于领域驱动设计（DDD）的 Spring Boot 3.x 项目，采用六边形架构实现业务与技术解耦。集成 MyBatis-Plus 数据访问层、Sa-Token 权限认证、Redis 分布式缓存、MapStruct 实体映射等核心组件，提供清晰的领域模型定义和模块化开发规范。

## 🥢技术栈

| 框架/工具 | 说明 | 版本 | 指南 |
|---------|-----|-----|-----|
| [Spring Boot](https://spring.io/projects/spring-boot) | 企业级开发框架 | 3.4.5 | [文档](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) |
| [MyBatis-Plus](https://baomidou.com/) | MyBatis 增强工具 | 3.5.7 | [文档](https://baomidou.com/pages/24112f/) |
| [Sa-Token](https://sa-token.cc/) | 权限认证框架 | 1.44.0 | [文档](https://sa-token.cc/doc.html) |
| [MapStruct](https://mapstruct.org/) | 实体映射框架 | 1.5.5.Final | [文档](https://mapstruct.org/documentation/stable/reference/html/) |
| [Hutool](https://hutool.cn/) | Java 工具集 | 5.8.24 | [文档](https://hutool.cn/docs/) |

## ☕运行配置

建议开发环境配置

| 依赖               | 版本要求   |
|-------------------|----------|
| JDK               | 17+      |
| Maven             | 3.6+     |
| MySQL             | 8.0+     |
| Redis             | 6.2+     |

## 🚴快速开始

### 🏀克隆项目

注：git版本需要大于2.25以上才能使用以下命令(稀疏检出)，若小于此版本可以选择clone全部文件，手动选出所需要的项目文件。

```bash
git clone --filter=blob:none --sparse https://gitee.com/snihao/focus-develop.git .
git sparse-checkout set springboot-ddd
```
