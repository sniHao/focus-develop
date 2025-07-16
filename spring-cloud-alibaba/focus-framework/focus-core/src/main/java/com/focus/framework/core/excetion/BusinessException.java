package com.focus.framework.core.excetion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private IBaseEnum basicEnum;
    private String customMessage;
    private Integer customCode = 100000;

    public BusinessException(IBaseEnum basicEnum) {
        this.basicEnum = basicEnum;
        this.customCode = basicEnum.getCode();
        this.customMessage = basicEnum.getMsg();
    }

    public BusinessException(String formatString, Object... args) {
        String stringMsg = formatStr(formatString, args);
        this.customMessage = stringMsg;
    }

    public BusinessException(Integer customCode, String formatString, Object... args) {
        String stringMsg = formatStr(formatString, args);
        this.customMessage = stringMsg;
        this.customCode = customCode;
    }

    public static String formatStr(String formatString, Object... args) {
        String[] parts = formatString.split("\\{\\}", -1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            result.append(parts[i]);
            if (i < args.length) {
                result.append(args[i]);
            }
        }
        return result.toString();
    }
}
