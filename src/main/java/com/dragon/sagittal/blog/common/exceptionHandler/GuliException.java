package com.dragon.sagittal.blog.common.exceptionHandler;


import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
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
        this.msg = myHttpStatus.getDESCRIPTION();
        this.code = myHttpStatus.getCODE();
    }

}
