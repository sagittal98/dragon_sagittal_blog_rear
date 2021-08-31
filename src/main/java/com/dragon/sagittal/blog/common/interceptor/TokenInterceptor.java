package com.dragon.sagittal.blog.common.interceptor;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dragon.sagittal.blog.common.config.JwtConfig;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 自定义token拦截器，进行token校验
 *
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 请求处理前调用（controller调用之前进行调用的方法）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("进入此处");
        /*统一进行拦截，查询当前session是否存在user）*/
        HttpSession session = request.getSession();
        Object token = session.getAttribute("TOKEN");
        if (null == token) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out;
            try {
                JSONObject res = new JSONObject();
                res.put("code", MyHttpStatus.TOKEN_IS_NULL.getCode());
                res.put("msg", MyHttpStatus.TOKEN_IS_NULL.getDescription());
                res.put("data", null);
                res.put("success", false);
                out = response.getWriter();
                out.append(res.toString());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        String tokenStr = token.toString();
        /* 对token进行校验 */
        boolean result = new JwtConfig().checkToken(tokenStr);
        if (result) {
            // 更新token
            String newToken = new JwtConfig().refreshToken(tokenStr);
            request.getSession().setAttribute("TOKEN", newToken);
            return true;
        } else {
            // 校验失败  返回校验失败信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out;
            try {
                JSONObject res = new JSONObject();
                res.put("code", MyHttpStatus.TOKEN_CHECK_FAIL.getCode());
                res.put("msg", MyHttpStatus.TOKEN_CHECK_FAIL.getDescription());
                res.put("data", null);
                res.put("success", false);
                out = response.getWriter();
                out.append(res.toString());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {

    }
}
