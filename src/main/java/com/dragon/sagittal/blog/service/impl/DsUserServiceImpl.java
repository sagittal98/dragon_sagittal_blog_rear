package com.dragon.sagittal.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.exceptionHandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.entity.DsUser;
import com.dragon.sagittal.blog.mapper.DsUserMapper;
import com.dragon.sagittal.blog.service.DsUserService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Service
public class DsUserServiceImpl extends ServiceImpl<DsUserMapper, DsUser> implements DsUserService {

    @Override
    public R login(DsUser dsUser) {
        QueryWrapper<DsUser> dsUserQueryWrapper = new QueryWrapper<>(dsUser);
        DsUser one = this.getOne(dsUserQueryWrapper);
        if (null == one) {
            throw new GuliException(MyHttpStatus.ACCOUNT_OR_PASSWORD_ERROR);
        }
        return R.ok();
    }

    @Override
    public R checkUserName(String userName) {
        QueryWrapper<DsUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userName);
        DsUser one = this.getOne(userQueryWrapper);
        if (null != one) {
            throw new GuliException(MyHttpStatus.USER_NAME_EXIST);
        }
        return R.ok().message("用户名不存在，可以注册！");
    }
}
