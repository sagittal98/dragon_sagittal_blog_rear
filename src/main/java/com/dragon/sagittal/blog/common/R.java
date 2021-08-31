package com.dragon.sagittal.blog.common;

import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * API返回参数类
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@Data
public class R implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private R() {
    }

    public static R ok() {
        return new R()
                .message(MyHttpStatus.SUCCESS.getDescription())
                .success(true)
                .code(MyHttpStatus.SUCCESS.getCode());
    }

    public static R error() {
        return new R()
                .message(MyHttpStatus.FAIL.getDescription())
                .success(false)
                .code(MyHttpStatus.FAIL.getCode());
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
