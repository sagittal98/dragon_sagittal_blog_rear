package com.dragon.sagittal.blog.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlogModel implements Serializable {
    @ApiModelProperty(value = "博客ID")
    @TableId(value = "blog_id", type = IdType.AUTO)
    private Long blogId;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    @ApiModelProperty(value = "博客内容")
    private String blogContent;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

}
