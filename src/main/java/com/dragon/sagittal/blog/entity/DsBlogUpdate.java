package com.dragon.sagittal.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ChunYu Sagittal
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DsBlogUpdate对象", description="")
public class DsBlogUpdate implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 更新ID
     */
    @TableId(value = "update_id", type = IdType.AUTO)
    private Long updateId;
    /**
     * 博客ID
     */
    private Long blogId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新描述
     */
    private String updateDescription;
}
