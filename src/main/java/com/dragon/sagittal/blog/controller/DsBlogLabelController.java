package com.dragon.sagittal.blog.controller;


import com.dragon.sagittal.blog.common.R;
import com.dragon.sagittal.blog.common.config.JwtConfig;
import com.dragon.sagittal.blog.common.exceptionHandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.entity.DsBlog;
import com.dragon.sagittal.blog.entity.DsBlogLabel;
import com.dragon.sagittal.blog.service.DsBlogLabelService;
import com.dragon.sagittal.blog.service.DsBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/serviceuser/ds-blog-label")
@CrossOrigin // 跨域配置
@Api(tags = "Blog Label API接口")
public class DsBlogLabelController {

    @Resource
    private DsBlogLabelService dsBlogLabelService;

    @Resource
    private DsBlogService dsBlogService;

    @ApiOperation(value = "设置单个博文标签")
    @PostMapping("/add/blog/label")
    public R addBlogLabel(@Valid @RequestBody DsBlogLabel dsBlogLabel, HttpServletRequest req) {
        /* 获取操作用户 */
        Object token = req.getSession().getAttribute("TOKEN");
        Long operateUserId = new JwtConfig().getUserId(token.toString());
        /* 获取博文作者 */
        DsBlog dsBlog = dsBlogService.getById(dsBlogLabel.getBlogId());
        if (null == dsBlog) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        if (!operateUserId.equals(dsBlog.getUserId())) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        /* 开始操作 */
        boolean save = dsBlogLabelService.save(dsBlogLabel);
        if (save) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.ADD_BLOG_LABEL_FAIL);
    }

    @ApiOperation(value = "设置多个博文标签")
    @PostMapping("/add/blog/labels")
    public R addBlogLabels(@Valid @RequestBody List<DsBlogLabel> dsBlogLabels, HttpServletRequest req) {
        /* 获取操作用户 */
        Object token = req.getSession().getAttribute("TOKEN");
        Long operateUserId = new JwtConfig().getUserId(token.toString());
        /* 获取博文作者 */
        DsBlog dsBlog = dsBlogService.getById(dsBlogLabels.get(0).getBlogId());
        if (null == dsBlog) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        if (!operateUserId.equals(dsBlog.getUserId())) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        /* 开始操作 */
        boolean save = dsBlogLabelService.saveBatch(dsBlogLabels);
        if (save) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.ADD_BLOG_LABEL_FAIL);
    }

    @ApiOperation(value = "根据ID删除单个博文标签")
    @DeleteMapping("delete/{blogLabelId}")
    public R deleteBlogLabel(
            @ApiParam(name = "blogLabelId", value = "博文标签ID", required = true)
            @PathVariable Long blogLabelId, HttpServletRequest req
    ) {
        /* 获取操作用户 */
        Object token = req.getSession().getAttribute("TOKEN");
        Long operateUserId = new JwtConfig().getUserId(token.toString());
        /* 获取博文作者 */
        DsBlog dsBlog = dsBlogService.getById(blogLabelId);
        if (null == dsBlog) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        if (!operateUserId.equals(dsBlog.getUserId())) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        boolean b = dsBlogLabelService.removeById(blogLabelId);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.DELETE_BLOG_LABAL_FAIL);
    }

    @ApiOperation(value = "删除多个博文标签")
    @DeleteMapping("/delete/blogLabels")
    public R deleteBlogLabels(@Valid @RequestBody List<Long> blogLabels, HttpServletRequest req) {
        /* 获取操作用户 */
        Object token = req.getSession().getAttribute("TOKEN");
        Long operateUserId = new JwtConfig().getUserId(token.toString());
        /* 获取博文作者 */
        DsBlog dsBlog = dsBlogService.getById(blogLabels.get(0));
        if (null == dsBlog) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        if (!operateUserId.equals(dsBlog.getUserId())) {
            throw new GuliException(MyHttpStatus.INSUFFICIENT_OPERATION_AUTHORITY);
        }
        boolean b = dsBlogLabelService.removeByIds(blogLabels);
        if (b) {
            return R.ok();
        }
        throw new GuliException(MyHttpStatus.DELETE_BLOG_LABAL_FAIL);
    }

}

