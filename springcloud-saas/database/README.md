# 数据库配置说明

## 概述

本项目使用MySQL作为主数据库，集成MyBatis-Plus框架，支持逻辑删除、乐观锁、自动填充等特性。

## 数据库初始化

### 1. 执行初始化脚本

```bash
# 连接到MySQL数据库
mysql -u root -p

# 执行初始化脚本
source /path/to/database/init.sql
```

### 2. 核心表说明

#### sys_user（用户表）
- **主键**：`id`（自增主键）
- **唯一索引**：`username`、`email`、`phone`
- **普通索引**：`status`、`create_time`、`deleted`
- **特性**：支持逻辑删除、乐观锁、自动填充

#### sys_base_data（基础数据表）
- **主键**：`id`（自增主键）
- **唯一索引**：`type + code`组合
- **普通索引**：`type`、`status`、`sort`、`create_time`、`deleted`
- **用途**：存储系统字典数据、配置参数等

## MyBatis-Plus特性配置

### 1. 逻辑删除
- **字段**：`deleted`
- **未删除值**：0
- **已删除值**：1
- **自动处理**：查询时自动过滤已删除数据，删除时自动更新删除标识

### 2. 乐观锁
- **字段**：`version`
- **机制**：更新时自动检查版本号，防止并发修改冲突

### 3. 自动填充
- **插入时自动填充**：
  - `create_time`：当前时间
  - `create_by`：创建者ID（当前为固定值1，可后续改为从上下文获取）
  - `update_time`：当前时间
  - `update_by`：更新者ID
  - `deleted`：0（未删除）

- **更新时自动填充**：
  - `update_time`：当前时间
  - `update_by`：更新者ID
  - `version`：版本号+1

## 数据字典设计

### 基础数据类型（sys_base_data.type）

1. **USER_STATUS** - 用户状态
   - 0：禁用
   - 1：启用

2. **GENDER** - 性别
   - 0：女
   - 1：男

3. **SYSTEM_CONFIG** - 系统配置
   - DEFAULT_PASSWORD：默认密码
   - LOGIN_RETRY_TIMES：登录重试次数

### 数据字典使用示例

```java
// 获取用户状态字典
List<BaseData> userStatusList = baseDataService.getByType("USER_STATUS");

// 获取系统配置
String defaultPassword = baseDataService.getValueByTypeAndCode("SYSTEM_CONFIG", "DEFAULT_PASSWORD");
```

## 视图说明

### v_user_info
用户信息视图，排除敏感字段（如密码），仅显示安全的用户信息。

### v_base_data_dict
基础数据字典视图，仅显示启用且未删除的数据，按类型和排序字段排序。

## 使用注意事项

1. **密码存储**：用户密码使用BCrypt加密存储
2. **逻辑删除**：删除操作会自动转换为逻辑删除，物理删除需要手动执行
3. **并发控制**：更新操作会自动检查版本号，建议在实体类操作前先查询最新版本
4. **字段验证**：用户名、邮箱、手机号具有唯一性约束
5. **索引优化**：查询时建议使用已建立索引的字段进行过滤

## 扩展建议

1. **用户权限表**：可考虑添加`sys_role`、`sys_permission`、`sys_user_role`等表
2. **操作日志表**：可添加`sys_operation_log`记录用户操作
3. **租户隔离**：如需支持多租户，可在BaseEntity中添加`tenant_id`字段
4. **分库分表**：数据量大时可考虑分库分表策略 
