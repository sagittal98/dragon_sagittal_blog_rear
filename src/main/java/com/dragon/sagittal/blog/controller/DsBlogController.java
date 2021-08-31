package com.dragon.sagittal.blog.controller;



import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.exceptionhandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.common.model.BlogModel;
import com.dragon.sagittal.blog.entity.DsBlog;
import com.dragon.sagittal.blog.service.DsBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Api(tags = "Blog API接口")
@RestController
@RequestMapping("/serviceuser/blog")
@CrossOrigin
public class DsBlogController {

    @Resource
    private DsBlogService dsBlogService;

    @ApiOperation(value = "创建博文/发布博文")
    @PostMapping(path = "/create")
    public R createBlog(@Valid @RequestBody BlogModel blogModel) {
        DsBlog dsBlog = new DsBlog(blogModel);
        // 博文发布时间
        dsBlog.setBlogReleaseTime(new Date(System.currentTimeMillis()));
        boolean save = dsBlogService.save(dsBlog);
        if (!save) {
            throw new GuliException(MyHttpStatus.CREAT_BLOG_FAIL);
        }
        return R.ok();
    }

    @ApiOperation(value = "从草稿箱发布博文")
    @GetMapping(path = "/drafts/release/{blogId}")
    public R draftsReleaseBlog(
            @ApiParam(name = "blogId", value = "博文ID", required = true)
            @PathVariable Long blogId
    ) {
        DsBlog blog = new DsBlog();
        /* 发布博文id */
        blog.setBlogId(blogId);
        /* 创建发布时间 */
        blog.setBlogReleaseTime(new Date(System.currentTimeMillis()));
        /* 从回收站移除 */
        blog.setRecoveryState(false);
        boolean b = dsBlogService.updateById(blog);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.CREAT_BLOG_FAIL);
    }

    @ApiOperation(value = "更新博文")
    @PutMapping(path = "/update")
    public R updateBlog(@Valid @RequestBody BlogModel blogModel) {
        boolean b = dsBlogService.updateById((DsBlog) blogModel);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.UPDATE_BLOG_FAIL);
    }

    @ApiOperation(value = "根据ID删除博文")
    @DeleteMapping(path = "/delete/{blogId}")
    public R deleteById(
            @ApiParam(name = "blogId", value = "博文ID", required = true)
            @PathVariable Long blogId
    ) {
        boolean b = dsBlogService.removeById(blogId);
        if (!b) {
            throw new GuliException(MyHttpStatus.DELETE_BLOG_FAIL);
        }
        return R.ok();
    }

    @ApiOperation(value = "根据ID查找博文")
    @GetMapping(path = "/select/{blogId}")
    public R selectById(
            @ApiParam(name = "blogId", value = "博文ID", required = true)
            @PathVariable Long blogId
    ) {
        DsBlog blog = dsBlogService.getById(blogId);
        if (null == blog) {
            throw new GuliException(MyHttpStatus.SELECT_BLOG_FAIL);
        }
        return R.ok().data("item", blog);
    }

    @ApiOperation(value = "批量删除博文")
    @DeleteMapping(path = "/delete/batch")
    public R deleteBatch(@Valid @RequestBody List<Long> blogIds) {
        boolean b = dsBlogService.removeByIds(blogIds);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.DELETE_BATCH_BLOG_FAIL);
    }

    @ApiOperation(value = "条件查找博文")
    @GetMapping(path = "/select/condition")
    public R selectCondition() {
        return null;
    }


    @ApiOperation(value = "博文存入草稿箱")
    @PutMapping(path = "/save/drafts")
    public R saveToDrafts(@Valid @RequestBody BlogModel blogModel) {
        DsBlog dsBlog = new DsBlog(blogModel);
        // 博文存入草稿箱，不发布，发布时间为null
        dsBlog.setRecoveryState(true);
        // 保存博文
        boolean save = dsBlogService.save(dsBlog);
        if (!save) {
            throw new GuliException(MyHttpStatus.DRAFTS_BLOG_FALI);
        }
        return R.ok();
    }


}

