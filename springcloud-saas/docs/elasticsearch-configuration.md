# Elasticsearch 配置说明

## 配置方式

通过配置属性 `spring.elasticsearch.enabled` 来控制是否启用 Elasticsearch 功能。

### 启用 Elasticsearch

在 `application.yml` 中添加以下配置：

```yaml
spring:
  elasticsearch:
    enabled: true
    uris: https://localhost:9200
    username: elastic
    password: your_password
    ssl:
      verification-mode: certificate
      certificate-authorities: es_key/http_ca.crt
```

### 禁用 Elasticsearch

如果服务不需要使用 Elasticsearch 功能，有以下几种方式：

#### 方式1：不配置（推荐）
不在配置文件中添加 `spring.elasticsearch` 相关配置，系统将自动禁用。

#### 方式2：显式禁用
```yaml
spring:
  elasticsearch:
    enabled: false
```

## 各服务配置状态

| 服务模块 | Elasticsearch状态 | 说明 |
|---------|------------------|------|
| focus-demo | ✅ 启用 | 需要使用向量搜索等功能 |
| focus-oauth | ❌ 禁用 | 纯认证服务，无需ES |
| focus-basedata | ❌ 禁用 | 基础数据服务，无需ES |

## 技术实现

使用 Spring Boot 的条件注解 `@ConditionalOnProperty` 实现：

```java
@Configuration
@ConditionalOnProperty(
    prefix = "spring.elasticsearch", 
    name = "enabled", 
    havingValue = "true", 
    matchIfMissing = false
)
public class ElasticsearchClientConfig {
    // 配置内容
}
```

### 注解说明
- `prefix = "spring.elasticsearch"`：配置前缀
- `name = "enabled"`：配置属性名
- `havingValue = "true"`：期望值为 true 时生效
- `matchIfMissing = false`：配置不存在时不生效（默认禁用）

## 优势

1. **简化配置**：无需手动管理复杂的包扫描路径
2. **按需加载**：只有需要的服务才会加载 ES 配置
3. **易于维护**：统一的开关控制，配置清晰
4. **避免错误**：自动避免因缺少 ES 配置导致的启动失败

## 注意事项

1. 启用 Elasticsearch 的服务需要确保配置完整（用户名、密码、SSL证书等）
2. 不使用 ES 的服务无需添加任何 ES 相关配置
3. 可以通过环境变量或配置中心动态控制启用状态 