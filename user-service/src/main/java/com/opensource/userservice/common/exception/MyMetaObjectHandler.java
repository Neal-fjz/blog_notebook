package com.opensource.userservice.common.exception;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description: mybatis-plus自动填充
 * @author: 风君子
 * @date:  2022/03/03 18:29
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String realName = StpUtil.getSession().getString("realName");
        //根据名称设置属性值
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String realName = StpUtil.getSession().getString("realName");
        // MetaObjectHandler提供的默认方法的策略均为:如果属性有值则不覆盖,如果填充值为null则不填充,所以先把属性值修改为null
        metaObject.setValue("updateTime",null);
        //根据名称设置属性值
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
