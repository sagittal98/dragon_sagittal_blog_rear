package com.dragon.sagittal.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DsBlogLabel对象", description="")
public class DsBlogLabel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 博客标签ID
     */
    @TableId(value = "blog_label_id", type = IdType.AUTO)
    private Long blogLabelId;
    /**
     * 标签ID
     */
    private Long labelId;
    /**
     * 博客ID
     */
    private Long blogId;
}
