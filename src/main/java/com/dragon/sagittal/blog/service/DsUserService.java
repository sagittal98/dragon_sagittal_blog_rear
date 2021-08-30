package com.dragon.sagittal.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.entity.DsUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
public interface DsUserService extends IService<DsUser> {

    /**
     * 登录接口
     * @param dsUser 登录数据
     */
    R login(DsUser dsUser);

    /**
     * 检查昵称接口
     * @param userName  待检查数据
     */
    R checkUserName(String userName);
}
