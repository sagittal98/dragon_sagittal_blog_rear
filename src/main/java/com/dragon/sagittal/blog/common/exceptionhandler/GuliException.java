package com.dragon.sagittal.blog.common.exceptionhandler;


import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 自定义异常类
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String  msg;


    public GuliException(MyHttpStatus myHttpStatus){
        this.msg = myHttpStatus.getDescription();
        this.code = myHttpStatus.getCode();
    }

}
