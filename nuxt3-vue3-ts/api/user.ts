// ============================================
// 用户相关接口定义
// ============================================

import request from './request';

/**
 * 用户登录
 * @param data 登录参数
 * @returns 返回用户信息和 token
 */
export const login = (data: LoginParams) =>
  request<LoginResponse>({
    url: 'user/login',
    method: 'post',
    data
  });

/**
 * 用户注册
 * @param data 注册参数
 * @returns 返回注册结果
 */
export const register = (data: RegisterParams) =>
  request<BaseResponse>({
    url: 'user/register',
    method: 'post',
    data
  });

/**
 * 根据 ID 获取用户信息
 * @param id 用户 ID
 * @returns 返回用户详细信息
 */
export const getUserById = (id: number) =>
  request<UserInfo>({
    url: `user/${id}`,
    method: 'get'
  });

/**
 * 获取用户列表（分页）
 * @param params 查询参数
 * @returns 返回用户列表和分页信息
 */
export const getUserList = (params?: UserListParams) =>
  request<PageResponse<UserInfo>>({
    url: 'user/list',
    method: 'get',
    data: params
  });

/**
 * 更新用户信息
 * @param data 用户信息
 * @returns 返回更新结果
 */
export const updateUser = (data: UpdateUserParams) =>
  request<BaseResponse>({
    url: 'user/update',
    method: 'put',
    data
  });

/**
 * 删除用户
 * @param id 用户 ID
 * @returns 返回删除结果
 */
export const deleteUser = (id: number) =>
  request<BaseResponse>({
    url: `user/${id}`,
    method: 'delete'
  });

/**
 * 用户登出
 * @returns 返回登出结果
 */
export const logout = () =>
  request<BaseResponse>({
    url: 'user/logout',
    method: 'post'
  });
