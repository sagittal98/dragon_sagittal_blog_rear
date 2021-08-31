package com.dragon.sagittal.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.exceptionhandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.entity.DsCategory;
import com.dragon.sagittal.blog.service.DsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/serviceuser/category")
@CrossOrigin
@Api(tags = "Category API接口")
public class DsCategoryController {

    @Resource
    private DsCategoryService dsCategoryService;

    @ApiOperation(value = "添加博文分类")
    @PostMapping(path = "/add")
    public R addBlogCategory(@Valid @RequestBody DsCategory dsCategory) {
        /* 校验数据 */
        boolean b = dsCategory.checkData();
        if (!b) {
            throw new GuliException(MyHttpStatus.CATEGORY_DATA_IS_NOT_EXIST);
        }
        /* 查询数据库是否存在 */
        QueryWrapper<DsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", dsCategory.getCategory());
        DsCategory one = dsCategoryService.getOne(queryWrapper);
        if (null == one) {
            throw new GuliException(MyHttpStatus.CATEGORY_DATA_IS_EXIST);
        }
        /* 写入数据库 */
        boolean save = dsCategoryService.save(dsCategory);
        if (save) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.ADD_CATEGORY_FAIL);
    }

    @ApiOperation(value = "根据ID删除博文分类")
    @DeleteMapping(path = "/delete/{categoryId}")
    public R deleteBlogCategory(
            @ApiParam(name = "categoryId", value = "分类ID", required = true)
            @PathVariable Long categoryId
    ) {
        boolean b = dsCategoryService.removeById(categoryId);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.DELETE_CATEGORY_FAIL);
    }

    @ApiOperation(value = "修改博文分类")
    @PutMapping(path = "/update/category")
    public R updateBlogCategory(@Valid @RequestBody DsCategory dsCategory) {
        /* 校验数据 */
        boolean b = dsCategory.checkDataAll();
        if (!b) {
            throw new GuliException(MyHttpStatus.CATEGORY_DATA_IS_NOT_EXIST);
        }
        /* 修改数据 */
        b = dsCategoryService.updateById(dsCategory);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.UPDATE_CATEGORY_FAIL);
    }

    @ApiOperation(value = "查找所有博客分类")
    @GetMapping(path = "/selectAll")
    public R selectAllBlogCategory() {
        Map<String, Object> map = dsCategoryService.getMap(null);
        return R.ok().data("item", map);
    }
}

