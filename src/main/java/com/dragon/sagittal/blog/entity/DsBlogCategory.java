package com.dragon.sagittal.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DsBlogCategory对象", description="")
public class DsBlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "blog_category_id", type = IdType.AUTO)
    private Long blogCategoryId;

    private Long categoryId;

    private Long blogId;
}
