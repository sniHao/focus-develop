package com.focus.framework.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MP注入处理器
 */
@Slf4j
@Component
public class InjectionMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作填充字段
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createBy", Long.class, 1L);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateBy", Long.class, 1L);
        this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
    }

    /**
     * 更新操作填充字段
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateBy", Long.class, 1L);
        this.strictUpdateFill(metaObject, "version", Long.class, getCurrentVersion(metaObject) + 1);
    }

    /**
     * 获取当前版本号
     */
    private Long getCurrentVersion(MetaObject metaObject) {
        Object version = getFieldValByName("version", metaObject);
        return version == null ? 0L : (Long) version;
    }

}
