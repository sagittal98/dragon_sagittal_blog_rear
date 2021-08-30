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
@ApiModel(value="DsLabel对象", description="")
public class DsLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long labelId;
    /**
     * 标签
     */
    private String label;

    /* 校验数据 */
    public boolean checkData() {
        return null != label;
    }

    /* 校验数据2 */
    public boolean checkDataALL() {
        return null != label || null != labelId;
    }
}
