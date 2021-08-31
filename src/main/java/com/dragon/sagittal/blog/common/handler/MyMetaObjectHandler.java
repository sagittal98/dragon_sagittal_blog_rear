package com.dragon.sagittal.blog.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 元数据实现自动注入数据类
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("用户注册数据开始初始化！");
        /*
         * 用户年龄  初始化18
         */
        this.setFieldValByName("userAge", 18, metaObject);
        this.setFieldValByName("userBirth", new Date(System.currentTimeMillis()), metaObject);
        /*
         * 用户性别  初始化0
         */
        this.setFieldValByName("userSex", 0, metaObject);
        /*
         * 用户头像 初始化
         */
        this.setFieldValByName("userAutograph", "https://www.baidu.com", metaObject);
        /*
         * 用户积分  初始化0.00
         */
        this.setFieldValByName("userIntegral", 0.00, metaObject);
        /*
         * 用户级别  初始化1
         */
        this.setFieldValByName("userLevel", 1, metaObject);
        /*
         * 用户权限 初始化0
         */
        this.setFieldValByName("userAuthority", 0, metaObject);
        /*
         * 创建时间  自动填充
         */
        this.setFieldValByName("userCreateTime", new Date(System.currentTimeMillis()), metaObject);
        /*
         * 登录时间  自动填充
         */
        this.setFieldValByName("userUpdateTime", new Date(System.currentTimeMillis()), metaObject);
        LOGGER.info("用户数据初始化完毕！");


        LOGGER.info("博客初始化开始！");
        /*
         * 博客创建时间
         */
        this.setFieldValByName("blogCreateTime", new Date(System.currentTimeMillis()), metaObject);
        /*
         * 博客发布时间
         *
         * 博客点赞次数、收藏次数、阅读次数
         */
        this.setFieldValByName("blogPraiseTimes", 0, metaObject);
        this.setFieldValByName("blogCollectionTimes", 0, metaObject);
        this.setFieldValByName("blogReadTimes", 0, metaObject);
        /*
         * 博客是否被删除/回收，默认false
         */
        this.setFieldValByName("isDeleted", false, metaObject);
        this.setFieldValByName("recoveryState", false, metaObject);
        LOGGER.info("博客初始化完毕！");

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
