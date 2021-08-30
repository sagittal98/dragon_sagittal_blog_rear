package com.dragon.sagittal.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.dragon.sagittal.blog.common.model.LoginUserModel;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "DsUser对象", description = "")
public class DsUser extends LoginUserModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户年龄
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userAge;

    /**
     * 用户生日
     */
    @TableField(fill = FieldFill.INSERT)
    private Date userBirth;

    /**
     * 用户性别  0：位置  1：女  2：男
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userSex;

    /**
     * 用户权限   0:普通  1：初级  2：中级  3：高级  4：特级  5：终级
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userAuthority;
    /**
     * 用户头像
     */
    @TableField(fill = FieldFill.INSERT)
    private String userAutograph;
    /**
     * 用户积分
     */
    @TableField(fill = FieldFill.INSERT)
    private Double userIntegral;

    /**
     * 用户等级   1：0积分  2:50积分  3:150积分  4：300积分
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer userLevel;
    /**
     * 用户创建时间
     */
    // 自动填充创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date userCreateTime;
    /**
     * 用户更新时间
     */
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date userUpdateTime;


    /**
     * 将注册或登录信息注入到用户里面
     * @param loginUserModel  目标信息
     */
    public DsUser (LoginUserModel loginUserModel){
        this.setUserId(loginUserModel.getUserId());
        this.setUserName(loginUserModel.getUserName());
        this.setUserPassword(loginUserModel.getUserPassword());
        this.setRoleId(loginUserModel.getRoleId());
    }

    public DsUser() {
    }
}
