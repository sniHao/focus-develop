package com.focus.common.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: Bean复制工具类，详细参考官网https://mapstruct.org/
 * @Author: ni_hao
 * @Date: 2025-06-18 15:18
 */
@Mapper(componentModel = "spring")
public interface FocusBeanCopy {
    FocusBeanCopy COPY = Mappers.getMapper(FocusBeanCopy.class);
}
