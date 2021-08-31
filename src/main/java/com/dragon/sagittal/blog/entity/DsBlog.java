package com.dragon.sagittal.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.dragon.sagittal.blog.common.model.BlogModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DsBlog对象", description = "")
public class DsBlog extends BlogModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博客创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date blogCreateTime;

    @ApiModelProperty(value = "博客点赞次数")
    @TableField(fill = FieldFill.INSERT)
    private Integer blogPraiseTimes;

    @ApiModelProperty(value = "博客收藏次数")
    @TableField(fill = FieldFill.INSERT)
    private Integer blogCollectionTimes;

    @ApiModelProperty(value = "博客阅读次数")
    @TableField(fill = FieldFill.INSERT)
    private Integer blogReadTimes;

    @ApiModelProperty(value = "博客发布时间")
    private Date blogReleaseTime;

    @ApiModelProperty(value = "回收状态")
    @TableField(fill = FieldFill.INSERT)
    private Boolean recoveryState;


    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean isDeleted;


    public DsBlog(BlogModel blogModel) {
        this.setBlogId(blogModel.getBlogId());
        this.setBlogContent(blogModel.getBlogContent());
        this.setBlogTitle(blogModel.getBlogTitle());
        this.setUserId(blogModel.getUserId());
    }

    public DsBlog() {

    }
}
