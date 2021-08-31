package com.dragon.sagittal.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.entity.DsUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
public interface DsUserService extends IService<DsUser> {

    /**
     * 用户登录接口
     *
     * @param dsUser 用户登录参数
     * @return 返回参数
     */
    R login(DsUser dsUser);

    /**
     * 待注册用户检查用户名是否存在数据库，如果存在则不能进行注册，反之可以注册
     *
     * @param userName 用户昵称
     * @return 返回参数
     */
    R checkUserName(String userName);
}
