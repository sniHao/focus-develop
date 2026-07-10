// ============================================
// 基础响应类型定义
// ============================================

/** 基础响应结构 */
interface BaseResponse {
  /** 响应代码 */
  code: number;
  /** 响应消息 */
  message: string;
  /** 是否成功 */
  success: boolean;
}

/** 分页响应结构 */
interface PageResponse<T> extends BaseResponse {
  data: {
    /** 数据列表 */
    list: T[];
    /** 总数 */
    total: number;
    /** 当前页码 */
    page: number;
    /** 每页大小 */
    pageSize: number;
  };
}

// ============================================
// 用户相关类型定义
// ============================================

/** 用户角色 */
type UserRole = 'admin' | 'user' | 'guest';

/** 用户信息 */
interface UserInfo {
  /** 用户 ID */
  id: number;
  /** 用户名 */
  username: string;
  /** 邮箱 */
  email: string;
  /** 头像 URL */
  avatar?: string;
  /** 用户角色 */
  role: UserRole;
  /** 创建时间 */
  createdAt: string;
  /** 更新时间 */
  updatedAt?: string;
}

/** 登录请求参数 */
interface LoginParams {
  /** 用户名 */
  username: string;
  /** 密码（前端加密后） */
  password: string;
  /** 是否记住登录 */
  remember?: boolean;
}

/** 登录响应数据 */
interface LoginResponse extends BaseResponse {
  data: {
    /** 认证 Token */
    token: string;
    /** 用户信息 */
    userInfo: UserInfo;
  };
}

/** 注册请求参数 */
interface RegisterParams {
  /** 用户名 */
  username: string;
  /** 密码 */
  password: string;
  /** 确认密码 */
  confirmPassword: string;
  /** 邮箱 */
  email: string;
  /** 验证码 */
  captcha: string;
}

/** 更新用户请求参数 */
interface UpdateUserParams {
  /** 用户 ID */
  id: number;
  /** 用户名（可选） */
  username?: string;
  /** 邮箱（可选） */
  email?: string;
  /** 头像 URL（可选） */
  avatar?: string;
}

/** 用户列表查询参数 */
interface UserListParams {
  /** 页码 */
  page?: number;
  /** 每页大小 */
  pageSize?: number;
  /** 搜索关键词 */
  keyword?: string;
  /** 用户角色 */
  role?: UserRole;
}

// ============================================
// 产品相关类型定义（示例）
// ============================================

/** 产品信息 */
interface ProductInfo {
  /** 产品 ID */
  id: number;
  /** 产品名称 */
  name: string;
  /** 产品描述 */
  description: string;
  /** 价格 */
  price: number;
  /** 库存 */
  stock: number;
  /** 产品图片 */
  images: string[];
  /** 创建时间 */
  createdAt: string;
}

/** 产品列表查询参数 */
interface ProductListParams {
  /** 页码 */
  page?: number;
  /** 每页大小 */
  pageSize?: number;
  /** 搜索关键词 */
  keyword?: string;
  /** 最低价格 */
  minPrice?: number;
  /** 最高价格 */
  maxPrice?: number;
}
