package com.focus.common.annotation;

/**
 * 限流类型枚举
 */
public enum LimiterType {

    /**
     * 默认全局限流
     */
    DEFAULT,

    /**
     * 按IP限流
     */
    IP
}
