package com.dragon.sagittal.blog.common.config;

import com.dragon.sagittal.blog.common.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * token令牌配置类，实现web层进行拦截然后校验token
 *
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
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
                        // 拦截 service user
                        "/serviceuser/**"
                )
                /*
                 * 排除拦截的地址
                 */
                .excludePathPatterns(
                        // 不拦截昵称检查
                        "/serviceuser/user/check/*",
                        // 不拦截账户注册及登录
                        "/serviceuser/user/add",
                        "/serviceuser/user/login",
                        // 不拦截公共接口
                        "/serviceuser/common/*",
                        // 不拦截静态资源
                        "/*.html",
                        "/*.js",
                        "/*.css");
    }
}
