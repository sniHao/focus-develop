package com.focus.user.convert;

import com.focus.model.entity.UserEntity;
import com.focus.model.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * 用户实体转换器
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    /**
     * 实体 -> VO
     */
    UserVO toVO(UserEntity entity);
}
