package com.dragon.sagittal.blog.controller;


import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.config.JwtConfig;
import com.dragon.sagittal.blog.common.exceptionHandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.common.model.LoginUserModel;
import com.dragon.sagittal.blog.entity.DsUser;
import com.dragon.sagittal.blog.service.DsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Api(tags = "User API接口")
@RestController
@RequestMapping("/serviceuser/user")
@CrossOrigin // 跨域配置
public class DsUserController {
    @Resource
    private DsUserService dsUserService;

    @ApiOperation(value = "根据Id查询单个用户")
    @GetMapping("/{userId}")
    public R selectById(
            @ApiParam(name = "userId", value = "用户ID", required = true)
            @PathVariable String userId) {
        DsUser user = dsUserService.getById(userId);
        if (null == user) {
            throw new GuliException(MyHttpStatus.ACCOUNT_IS_NOT_EXIST);
        }
        return R.ok().data("item", user);
    }

    @ApiOperation(value = "用户名检查")
    @GetMapping("/check/{userName}")
    public R checkUserName(
            @ApiParam(name = "userName", value = "待查用户昵称", required = true)
            @PathVariable String userName) {
        return dsUserService.checkUserName(userName);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public R addUser(@Valid @RequestBody LoginUserModel userModel, HttpServletRequest req) {
        /* 数据检查 */
        boolean checkDsUser = userModel.checkDsUser();
        if (!checkDsUser) {
            throw new GuliException(MyHttpStatus.USER_INFO_IS_NOT_COMPLETE);
        }
        DsUser dsUser = new DsUser(userModel);
        boolean save = dsUserService.save(dsUser);
        userModel.setUserId(dsUser.getUserId());
        String token = new JwtConfig().createToken(userModel.toString());
        if (!save) {
            throw new GuliException(MyHttpStatus.USER_REGISTER_FAIL);
        }
        // 设置session token
        req.getSession().setAttribute("TOKEN", token);
        return R.ok();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public R login(@Valid @RequestBody LoginUserModel loginUserModel, HttpServletRequest req) {
        boolean checkLogin = loginUserModel.checkLogin();
        if (!checkLogin) {
            throw new GuliException(MyHttpStatus.LOGIN_INFO_IS_NOT_COMPLETE);
        }
        DsUser dsUser = new DsUser(loginUserModel);
        loginUserModel.setUserId(dsUser.getUserId());
        String token = new JwtConfig().createToken(loginUserModel.toString());
        // 设置session token
        req.getSession().setAttribute("TOKEN", token);
        return dsUserService.login(dsUser);
    }

    @ApiOperation(value = "根据ID删除单个用户")
    @DeleteMapping("/{userId}")
    public R removeUserById(@PathVariable Long userId) {
        boolean b = dsUserService.removeById(userId);
        if (!b) {
            throw new GuliException(MyHttpStatus.DELETE_ACCOUNT_FAIL);
        }
        return R.ok().message("账户删除成功！");
    }
}

