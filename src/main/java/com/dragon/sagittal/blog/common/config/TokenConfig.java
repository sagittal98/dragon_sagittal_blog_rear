package com.dragon.sagittal.blog.common.config;

import com.dragon.sagittal.blog.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class TokenConfig implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * 对自定义拦截器进行注册
         */
        registry.addInterceptor(tokenInterceptor)
                /*
                 * 路径拦截：所有的路径都将被拦截
                 */
                .addPathPatterns(
                        "/serviceuser/**" /* 拦截serviceuser */
                )
                /*
                 * 排除拦截的地址
                 */
                .excludePathPatterns(
                        /* 静态资源不拦截 */
                        "/serviceuser/user/check/*",/* 不拦截昵称检查 */
                        "/serviceuser/user/add",/* 账户注册不拦截 */
                        "/serviceuser/user/login",/* 账户登录不拦截 */
                        "/serviceuser/common/*",/* 公共接口不拦截 */
                        "/*.html", /* 静态资源不拦截 */
                        "/*.js", /* 静态资源不拦截 */
                        "/*.css");
    }
}
