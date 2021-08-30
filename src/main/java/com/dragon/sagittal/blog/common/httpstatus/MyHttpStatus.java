package com.dragon.sagittal.blog.common.httpstatus;

public enum MyHttpStatus {

    SUCCESS(20000, "请求成功！"),
    TOKEN_CHECK_FAIL(20001,"token校验失败！"),
    TOKEN_IS_NULL(20002,"token不存在！"),
    FAIL(20003,"请求失败！"),
    TOKEN_EXPIRE(20004,"token过期！"),
    LOGIN_INFO_IS_NOT_COMPLETE(20005,"账户或密码不完整！"),
    USER_INFO_IS_NOT_COMPLETE(20006,"账户或密码不完整！"),
    USER_REGISTER_FAIL(20007,"账户注册失败！"),
    USER_NAME_EXIST(20008,"用户名已经存在！"),
    ACCOUNT_OR_PASSWORD_ERROR(20009,"账户或密码错误！"),
    ACCOUNT_IS_NOT_EXIST(20010,"账户信息不存在！"),
    DELETE_ACCOUNT_FAIL(20011,"删除用户失败！"),
    ADD_ROLE_FAIL(20012,"添加角色失败！"),
    DELETE_ROLE_FAIL(20013,"删除角色失败！"),
    GET_TOKEN_USER_ID_FAIL(20014,"获取token中用户id失败"),
    CREAT_BLOG_FAIL(20015,"博文发布失败！"),
    SELECT_BLOG_FAIL(20016,"博文不存在！"),
    DRAFTS_BLOG_FALI(20017,"博文存草稿失败！"),
    DELETE_BLOG_FAIL(20018,"博文删除失败！"),
    UPDATE_BLOG_FAIL(20019,"博文更新失败！"),
    DELETE_BATCH_BLOG_FAIL(20020,"批量删除博文失败！"),
    CATEGORY_DATA_IS_NOT_EXIST(20021,"添加博文分类信息为空！"),
    CATEGORY_DATA_IS_EXIST(20022,"添加博文分类信息以及存在数据库中！不可重复添加！"),
    ADD_CATEGORY_FAIL(20023,"添加博文分类失败！"),
    DELETE_CATEGORY_FAIL(20024,"删除博文分类失败！"),
    UPDATE_CATEGORY_FAIL(20025,"修改博文分类失败！"),
    ADD_LABEL_FAIL(20026,"添加标签失败!"),
    DELETE_LABEL_FAIL(20027,"删除标签失败!"),
    UPDATE_LABEL_FAIL(20028,"修改标签失败!"),
    SELECT_LABEL_FAIL(20029,"查找标签失败!"),
    LABEL_DATA_IS_NOT_COMPLETE(20030,"标签数据不完整！"),
    INSUFFICIENT_OPERATION_AUTHORITY(20031,"博文操作权限不够！"),
    ADD_BLOG_LABEL_FAIL(20032,"插入博文标签失败！"),
    DELETE_BLOG_LABAL_FAIL(20033,"删除博文标签失败！")

    ;


    /* 代号 */
    private final int CODE;
    /* 说明描述 */
    private final String DESCRIPTION;


    private MyHttpStatus(int code, String description) {
        this.CODE = code;
        this.DESCRIPTION = description;
    }


    public int getCODE() {
        return this.CODE;
    }


    public String getDESCRIPTION(){
        return this.DESCRIPTION;
    }
}
