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
@ApiModel(value="DsUserFans对象", description="")
public class DsUserFans implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fans_id", type = IdType.AUTO)
    private Long fansId;

    private Long fansUserId;

    private Long userId;

    private Date fansCreateTime;


}
