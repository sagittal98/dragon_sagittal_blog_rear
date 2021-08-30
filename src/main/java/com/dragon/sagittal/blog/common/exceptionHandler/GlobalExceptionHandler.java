package com.dragon.sagittal.blog.common.exceptionHandler;

import com.dragon.sagittal.blog.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j  // 可以将异常输出到文件中去
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
        return R.error().message("不知道发生了什么异常！");
    }

    /**
     * 自定义异常处理方法
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        // 会将异常写入到error中去
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode()).data(null);
    }
}
