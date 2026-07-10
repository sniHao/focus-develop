---
skill: nuxt3-coding-standards
description: 检查 Nuxt3 + Vue3 + TypeScript 项目代码是否符合规范（手动检查工具）
tags: [nuxt3, vue3, typescript, coding-standards]
enabled: true
---

# 编码规范检查工具

> **重要说明**：此 Skill 是**手动检查工具**，不会自动生效。项目的编码规范已写入 `CLAUDE.md`，AI 会自动遵守。

## 使用场景
- 代码提交前的质量检查
- 代码审查时的规范验证
- 重构代码时的合规性确认

## 检查范围

### 1. 环境配置检查
- [ ] `.env.dev` 和 `.env.pro` 文件是否存在？
- [ ] 环境变量是否包含必需字段（API_URL、SECRET_KEY）？
- [ ] 是否存在硬编码的 API 地址或密钥？
- [ ] `package.json` 中是否配置了多环境打包脚本？
- [ ] ❌ **是否直接读取了 `.env` 开头的文件？（严禁）**

**安全规范**：
- ❌ **禁止直接读取 `.env.*` 文件**（如 `fs.readFileSync('.env.dev')`）
- ✅ 必须通过 `useRuntimeConfig()` 或 `process.env` 获取环境变量
- ✅ 敏感信息不得硬编码在代码中

### 2. API 接口规范检查（扫描 `api/**/*.ts`）
- [ ] 是否按业务模块划分文件（user.ts, product.ts）？
- [ ] 每个接口函数是否有 JSDoc 注释（@param, @returns）？
- [ ] 请求参数和响应数据是否定义了类型？
- [ ] 是否使用了 `request<ResponseType>()` 泛型约束？
- [ ] 是否避免使用 `any` 类型？

**❌ 不符合规范：**
```typescript
export const api1 = (data: any) => request({ url: 'xxx', method: 'post', data });
```

**✅ 符合规范：**
```typescript
/** 用户登录 */
export const login = (data: LoginRequest) =>
  request<LoginResponse>({ url: '/auth/login', method: 'post', data });
```

### 3. 类型定义规范检查（扫描 `types/**/*.d.ts`）
- [ ] 每个 interface 是否有 JSDoc 注释？
- [ ] 每个字段是否有注释说明？
- [ ] 是否优先使用 interface（而非 type）？
- [ ] 是否避免使用 `any` 类型？

### 4. 组件开发规范检查（扫描 `components/**/*.vue`）
- [ ] 是否使用 `<script setup lang="ts">` 语法？
- [ ] Props 和 Emits 是否定义了 TypeScript 类型？
- [ ] 是否优先使用 Naive UI 组件（NButton, NCard 等）？
- [ ] 单个组件是否超过 300 行（需拆分）？

### 5. 页面开发规范检查（扫描 `pages/**/*.vue`）
- [ ] 是否将功能封装为独立组件？
- [ ] 页面文件是否超过 500 行（需拆分）？
- [ ] 是否使用 `useHead` 配置 SEO 信息？
- [ ] API 请求是否使用 try-catch 错误处理？
- [ ] 异步操作是否有 loading 状态？

### 6. 样式规范检查
- [ ] 是否优先使用 Tailwind CSS 工具类？
- [ ] 自定义 CSS 是否有充分理由（需注释说明）？
- [ ] 组件样式是否添加 `scoped` 属性？
- [ ] 是否避免使用 `!important`？

### 7. Git 提交规范检查
- [ ] Commit message 是否符合格式：`<type>: <subject>`？
- [ ] Type 是否正确（feat, fix, docs, style, refactor, perf, test, chore）？

---

## 检查报告示例

```markdown
# 编码规范检查报告

**检查时间：** 2024-01-01 10:00:00  
**检查范围：** api/user.ts, components/UserCard.vue

## 严重问题（必须修复）：2 项
❌ api/order.ts:23 - 使用了 any 类型，需定义明确类型
❌ pages/user/profile.vue - 代码超过 500 行，需拆分组件

## 建议改进（推荐修复）：3 项
⚠️ api/product.ts:15 - 缺少 JSDoc 注释
⚠️ components/UserCard.vue - Props 缺少类型定义
⚠️ pages/product/list.vue - 缺少 SEO 配置

## 符合规范：8 项
✅ api/user.ts - 注释完整，类型定义规范
✅ components/home/Nav.vue - 代码结构规范
✅ 使用了 Tailwind CSS
...
```

---

## 使用方式

```bash
# 检查整个项目
/nuxt3-coding-standards

# 检查特定目录
/nuxt3-coding-standards api/

# 检查特定文件
/nuxt3-coding-standards components/user/UserCard.vue
```

---

## 重要提醒

1. ✅ **自动规范遵守**：AI 会自动读取 `CLAUDE.md` 中的规范，日常开发中会自动遵守
2. ✅ **手动检查工具**：此 Skill 用于手动检查代码质量，生成详细报告
3. ✅ **提交前检查**：建议在 Git 提交前运行此检查
4. ❌ **不会自动修改**：此工具仅检查和建议，不会自动修改代码

---

## 相关文档
- 完整编码规范：`CLAUDE.md`（AI 会自动遵守）
- 环境配置：`.env.dev`, `.env.pro`

