package com.dragon.sagittal.blog.controller;


import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.exceptionhandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.entity.DsRole;
import com.dragon.sagittal.blog.service.DsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Api(tags = "Role API 接口")
@RestController
@RequestMapping("/serviceuser/role")
@CrossOrigin
public class DsRoleController {

    @Resource
    private DsRoleService dsRoleService;

    @ApiOperation(value = "添加角色")
    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public R addRole(@Valid @RequestBody DsRole dsRole) {
        boolean b = dsRoleService.save(dsRole);
        if (!b) {
            throw new GuliException(MyHttpStatus.ADD_ROLE_FAIL);
        }
        return R.ok().message("添加角色成功！");
    }

    @ApiOperation(value = "获取所有角色信息")
    @GetMapping(path = "/selects")
    public R selectAll() {
        List<DsRole> list = dsRoleService.list(null);
        return R.ok().data("item", list);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/{roleId}")
    public R removeRole(@PathVariable Long roleId) {
        boolean b = dsRoleService.removeById(roleId);
        if (!b) {
            throw new GuliException(MyHttpStatus.DELETE_ROLE_FAIL);
        }
        return R.ok().message("删除角色成功！");
    }
}

