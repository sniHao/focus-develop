# Nuxt3 + Vue3 + TypeScript 项目编码规范

> **重要说明**：本文件会被 Claude Code 自动加载，AI 在开发时会自动遵守这些规范。

## 🌍 环境变量配置

### 环境文件
- `.env.dev`：开发环境配置
- `.env.pro`：生产环境配置
- `.env.example`：环境变量模板

### 环境变量定义
```bash
# API 基础地址
NUXT_PUBLIC_API_URL=https://api.example.com
NUXT_PUBLIC_WS_URL=wss://api.example.com/ws

# 密钥配置
SECRET_KEY=your-secret-key-here
```

### 构建时环境切换
- 开发构建：`npm run build:dev`
- 生产构建：`npm run build:pro`

### ⚠️ 安全规范
- ❌ **禁止直接读取 `.env` 开头的文件**（`.env.dev`、`.env.pro`、`.env.local` 等）
- ✅ 必须通过 `useRuntimeConfig()` 或 `process.env` 获取环境变量
- ✅ 敏感信息（SECRET_KEY）不得硬编码在代码中
- ✅ `.env.*` 文件已加入 `.gitignore`，禁止提交到版本控制

---

## 📁 项目文件组织规范

### 目录结构
```
api/              # API 接口定义（按模块拆分）
├── request.ts    # 请求封装（统一配置、拦截器）
├── user.ts       # 用户相关接口
├── product.ts    # 产品相关接口
└── ...           # 其他业务模块

components/       # 可复用组件
pages/            # 页面路由
composables/      # 组合式函数
types/            # TypeScript 类型定义
utils/            # 工具函数
```

### 禁止事项
- ❌ 禁止在单个 `.vue` 文件中实现完整页面逻辑（必须拆分组件和逻辑）

---

## 🔌 API 接口开发规范

### 文件组织
- **一个业务模块一个文件**：`api/moduleName.ts`

### 接口定义规范
```typescript
import request from './request';

// ============================================
// 类型定义（必须在接口前定义）
// ============================================

/**
 * 用户登录请求参数
 */
export interface LoginRequest {
  /** 用户名 */
  username: string;
  /** 密码 */
  password: string;
}

/**
 * 用户登录响应数据
 */
export interface LoginResponse {
  /** 访问令牌 */
  token: string;
  /** 用户信息 */
  user: {
    id: number;
    username: string;
    email: string;
  };
}

// ============================================
// 接口函数定义
// ============================================

/**
 * 用户登录
 * @param data 登录信息
 * @returns 登录结果（包含 token 和用户信息）
 */
export const login = (data: LoginRequest) => {
  return request<LoginResponse>({
    url: '/auth/login',
    method: 'post',
    data,
  });
};

/**
 * 获取用户详情
 * @param id 用户 ID
 * @returns 用户详细信息
 */
export const getUserById = (id: number) => {
  return request<UserDetail>({
    url: `/user/${id}`,
    method: 'get',
  });
};
```

### 强制要求
1. ✅ **所有接口必须定义 Request 和 Response 类型**
2. ✅ **类型定义必须添加 JSDoc 注释**（描述字段含义）
3. ✅ **接口函数必须添加 JSDoc 注释**（描述参数、返回值）
4. ✅ **使用泛型约束响应类型**：`request<ResponseType>()`

---

## 🎨 样式开发规范

### Tailwind CSS 优先
- ✅ **强制使用 Tailwind CSS 编写样式**
- ❌ 禁止编写自定义 CSS（除非 Tailwind 无法实现）
- ✅ 使用 Tailwind 的响应式工具类：`sm:` `md:` `lg:` `xl:`

### 自定义样式（例外情况）
仅在以下场景允许自定义 CSS：
- 复杂动画效果
- Tailwind 无法实现的特殊布局
- 第三方库样式覆盖

```vue
<style scoped>
/* 必须添加注释说明为什么需要自定义样式 */
.custom-animation {
  animation: fade-in 0.3s ease-in-out;
}
</style>
```

---

## 🧩 组件开发规范

### Naive UI 组件优先
- ✅ **强制使用 Naive UI 组件库**（按钮、表单、表格、弹窗等）
- ❌ 禁止自己编写基础 UI 组件（按钮、输入框、弹窗等）
- ✅ 使用 Naive UI 的主题定制功能

### 组件拆分原则
- ✅ **页面组件必须拆分为多个子组件**（每个组件职责单一）
- ❌ 禁止单个 `.vue` 文件超过 300 行
- ✅ 可复用组件放入 `components/` 目录
- ✅ 页面专用组件放入 `components/[PageName]/` 子目录

### 组件命名规范
```
components/
├── UserCard.vue          # 可复用组件（PascalCase）
├── ProductList.vue       # 可复用组件
└── HomePage/             # 页面专用组件目录
    ├── HeroSection.vue   # 首页英雄区
    ├── FeatureList.vue   # 首页功能列表
    └── Testimonials.vue  # 首页用户评价
```

---

## 📄 页面开发规范

### 单文件组件结构
```vue
<script setup lang="ts">
// 1. 导入（分组：类型、组件、API、工具）
import type { UserInfo } from '~/types/user';
import { NButton, NCard } from 'naive-ui';
import { getUserById } from '~/api/user';
import { formatDate } from '~/utils/format';

// 2. Composables（组合式函数）
const router = useRouter();
const { userId } = useRoute().params;

// 3. 响应式数据
const userInfo = ref<UserInfo | null>(null);
const loading = ref(false);

// 4. 方法定义
const fetchUserInfo = async () => {
  loading.value = true;
  try {
    const res = await getUserById(Number(userId));
    userInfo.value = res.data;
  } catch (error) {
    console.error('获取用户信息失败', error);
  } finally {
    loading.value = false;
  }
};

// 5. 生命周期
onMounted(() => {
  fetchUserInfo();
});
</script>

<template>
  <div class="container mx-auto px-4 py-8">
    <n-card v-if="userInfo" title="用户信息">
      <!-- 模板内容 -->
    </n-card>
  </div>
</template>

<style scoped>
/* 仅在必要时添加样式（优先使用 Tailwind） */
</style>
```

### 强制要求
1. ✅ 使用 `<script setup lang="ts">` 语法
2. ✅ 导入语句必须分组（类型、组件、API、工具）
3. ✅ 复杂逻辑必须提取为 Composable 函数
4. ✅ 页面超过 200 行必须拆分组件

---

## 🔧 TypeScript 规范

### 类型定义位置
- `types/` 目录：存放全局类型定义
- 接口文件内：存放接口相关的 Request/Response 类型
- 组件文件内：存放组件内部私有类型

### 强制类型约束
```typescript
// ✅ 好的做法
interface User {
  id: number;
  name: string;
  email: string;
}

const user: User = { id: 1, name: '张三', email: 'zhang@example.com' };

// ❌ 禁止使用 any
const data: any = {}; // 禁止！

// ✅ 使用 unknown 代替 any
const data: unknown = {};
if (typeof data === 'object') {
  // 类型收窄后使用
}
```

---

## ✅ 代码质量要求

### 必须遵守
1. ✅ **所有函数、接口、类型必须添加 JSDoc 注释**
2. ✅ **禁止使用 `any` 类型**（使用 `unknown` 并进行类型收窄）
3. ✅ **组件必须模块化**（单个文件不超过 300 行）
4. ✅ **使用 Tailwind CSS 编写样式**
5. ✅ **使用 Naive UI 组件库**
6. ✅ **API 接口必须定义类型**

### 代码审查检查点
- □ 是否使用了 Tailwind CSS？
- □ 是否使用了 Naive UI 组件？
- □ 是否拆分了组件？
- □ 是否定义了接口类型？
- □ 是否添加了 JSDoc 注释？
- □ 是否避免使用 `any` 类型？

---

## 📝 提交规范

### Commit 信息格式
```
<type>: <subject>

<body>
```

### Type 类型
- `feat`: 新功能
- `fix`: 修复 Bug
- `docs`: 文档更新
- `style`: 代码格式调整（不影响功能）
- `refactor`: 重构代码
- `perf`: 性能优化
- `test`: 测试相关
- `chore`: 构建/工具链相关

### 示例
```
feat: 新增用户登录功能

- 实现登录表单组件
- 集成登录 API 接口
- 添加 token 存储逻辑
```

---

## 🚀 开发流程

### 新增功能流程
1. **定义类型**：在 `types/` 或接口文件中定义类型
2. **创建 API**：在 `api/` 目录创建接口函数
3. **开发组件**：在 `components/` 创建可复用组件
4. **编写页面**：在 `pages/` 使用组件组装页面
5. **测试验证**：本地测试功能完整性
6. **代码审查**：自检是否符合规范（可使用 `/nuxt3-coding-standards` 命令）

---

## 🔍 代码审查命令

### 手动检查规范
```bash
/nuxt3-coding-standards
```

此命令会检查：
- API 接口是否定义了类型
- 组件是否过大（超过 300 行）
- 是否使用了 Tailwind CSS
- 是否使用了 Naive UI 组件
- 是否添加了必要的注释

---

## 📚 参考资源

- [Nuxt 3 官方文档](https://nuxt.com/)
- [Vue 3 官方文档](https://cn.vuejs.org/)
- [Naive UI 组件库](https://www.naiveui.com/)
- [Tailwind CSS 文档](https://tailwindcss.com/)
