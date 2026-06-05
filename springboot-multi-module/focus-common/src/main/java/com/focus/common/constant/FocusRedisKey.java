package com.focus.common.constant;

/**
 * Redis键枚举
 */
public enum FocusRedisKey {

    SIGN_REPLAY("sign:replay:", "签名防重放");

    private final String value;
    private final String tips;

    FocusRedisKey(String value, String tips) {
        this.value = value;
        this.tips = tips;
    }

    public String value() {
        return value;
    }

    public String tips() {
        return tips;
    }
}
