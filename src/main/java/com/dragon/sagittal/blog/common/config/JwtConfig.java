package com.dragon.sagittal.blog.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dragon.sagittal.blog.common.exceptionHandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.common.model.LoginUserModel;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration  /* jwt配置类 */
@Data
@Component  /* 配置自动被装在 */
public class JwtConfig {

    @Value("${user.jwt.SECRET}")
    private String SECRET;

    @Value("${user.jwt.EXPIRES}")
    private Long EXPIRES;

    @Value("${user.jwt.ISSUER}")
    private String ISSUER;

    /**
     * 创建token
     *
     * @param claim 保密参数
     */
    public String createToken(String claim) {
        // HS256加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(new Date(System.currentTimeMillis()))  /* 签发日期 */
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRES)) /* 有效期 */
                .claim("data", claim)  /* 键值对 */
                .setIssuer(ISSUER)   /* 签发人 */
                .signWith(signatureAlgorithm, SECRET);
        return jwtBuilder.compact();
    }

    /**
     * token是否过期
     *
     * @param token token
     */
    public boolean checkToken(String token) {
        return null != claim(token);
    }

    /**
     * 更新token
     *
     * @param token token
     */
    public String refreshToken(String token) {
        String claim = claim(token);
        return createToken(claim);
    }

    /**
     * 获取用户ID
     *
     * @param token token
     */
    public Long getUserId(String token) {
        String claim = claim(token);
        try {
            JSONObject jsonObject = JSONObject.parseObject(claim);
            Object userId = jsonObject.get("userId");
            return Long.valueOf(userId.toString());
        } catch (JSONException e) {
            throw new GuliException(MyHttpStatus.GET_TOKEN_USER_ID_FAIL);
        }
    }

    /*
     * 从token中获取登录信息
     *
     * @param token token
     */
    public LoginUserModel getUserModel(String token) {
        String claim = claim(token);
        try {
            JSONObject jsonObject = JSONObject.parseObject(claim);
            return new LoginUserModel(jsonObject);
        } catch (JSONException e) {
            throw new GuliException(MyHttpStatus.GET_TOKEN_USER_ID_FAIL);
        }
    }


    /**
     * 解析token
     *
     * @param token token
     */
    private String claim(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            Object data = body.get("data");
            return data.toString();
        } catch (ExpiredJwtException e) {
            throw new GuliException(MyHttpStatus.TOKEN_EXPIRE.getCODE(), MyHttpStatus.TOKEN_EXPIRE.getDESCRIPTION());
        }
    }
}
