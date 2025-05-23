package nh.focus.common.constant;

/**
 * @Description:响应状态码
 * @Author: ni_hao
 * @Date: 2025-04-23 15:42
 */
public enum ResultCode {
    SUCCESS("成功", "200"),
    CLIENT_ERROR("客户端发生错误", "A0001"),
    USER_REGISTER_ERROR("用户注册错误", "A0100"),
    USER_LOGIN_EXCEPTION("用户登录异常", "A0200"),
    PERMISSION_EXCEPTION("访问权限异常", "A0300"),
    REQUEST_PARAMETER_ERROR("请求参数错误", "A0400"),
    REQUEST_SERVICE_EXCEPTION("请求服务异常", "A0500"),
    USER_RESOURCE_EXCEPTION("用户资源异常", "A0600"),
    FILE_UPLOAD_EXCEPTION("上传文件异常", "A0700"),
    SYSTEM_ERROR("系统发生错误", "B0001"),
    SYSTEM_EXECUTION_TIMEOUT("系统执行超时", "B0100"),
    SYSTEM_OFTEN("访问过于频繁，请稍候再试", "B0200"),
    SYSTEM_RESOURCE_EXCEPTION("系统资源异常", "B0300"),
    THIRD_PARTY_SERVICE_ERROR("第三方服务发生错误", "C0001"),
    MIDDLEWARE_SERVICE_ERROR("中间件服务出错", "C0100"),
    DATABASE_SERVICE_ERROR("数据库服务出错", "C0300"),
    NOTIFICATION_SERVICE_ERROR("通知服务出错", "C0500");
    private final String tips;
    private final String code;

    ResultCode(String tips, String code) {
        this.tips = tips;
        this.code = code;
    }

    public String tips() {
        return tips;
    }

    public String code() {
        return code;
    }

}
