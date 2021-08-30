package com.dragon.sagittal.blog.common.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.Serializable;

@Data
public class LoginUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户角色id
     */
    private Long roleId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /* 数据检查 */
    public boolean checkDsUser() {
        return null != this.userPassword || null != this.roleId || null != this.userName;
    }

    /* 登录检查 */
    public boolean checkLogin() {
        return null != this.userName || null != this.userPassword;
    }

    public LoginUserModel(JSONObject jsonObject) {
        this.setRoleId(jsonObject.getLong("roleId"));
        this.setUserName(jsonObject.getString("userName"));
        this.setUserPassword(jsonObject.getString("password"));
        this.setUserId(jsonObject.getLong("userId"));
    }

    /**
     * 空构造器
     */
    public LoginUserModel() {
    }

    /**
     * 重写为JSON格式
     */
    @SneakyThrows
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("roleId", roleId);
        jsonObject.put("userName", userName);
        jsonObject.put("userPassword", userPassword);
        return jsonObject.toString();
    }
}
