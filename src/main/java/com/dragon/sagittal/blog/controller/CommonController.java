package com.dragon.sagittal.blog.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共接口类
 */
@RestController
@RequestMapping("/serviceuser/common")
@Api(tags = "Common API接口")
public class CommonController {
    /*
        1.获取博客所有文章
        2.获取单个博客所有信息
        3.获取单个博客博主信息
        4.获取博主上一篇及下一篇信息
        5.获取博主所有博客
        6.获取博主分类博客
        7.获取博主博客所有评论
        8.获取相关博客的推荐博客
    */

}
