package com.dragon.sagittal.blog.common.config;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dragon.sagittal.blog.common.exceptionhandler.GuliException;
import com.dragon.sagittal.blog.common.httpstatus.MyHttpStatus;
import com.dragon.sagittal.blog.common.model.LoginUserModel;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT配置类
 *
 * @author ChunYu Sagittal
 * @date 2021/8/31
 */
@Configuration
@Data
@Component
public class JwtConfig {

    @Value("${user.jwt.SECRET}")
    private String secret;

    @Value("${user.jwt.EXPIRES}")
    private Long expires;

    @Value("${user.jwt.ISSUER}")
    private String issuer;


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
                // 签发日期
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 有效期时长
                .setExpiration(new Date(System.currentTimeMillis() + expires))
                // 加密信息
                .claim("data", claim)
                // 签发人
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, secret);
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
     * 通过解密token令牌获取加密信息中的用户ID
     *
     * @param token token令牌
     * @return 返回加密信息里的用户ID【Long】
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

    /**
     * 从token令牌中获取加密的用户信息
     *
     * @param token token令牌
     * @return 返回登录模型
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
     * 解密token，获取加密信息字符串
     *
     * @param token token令牌
     * @return 返回加密信息【String】
     */
    private String claim(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            Object data = body.get("data");
            return data.toString();
        } catch (ExpiredJwtException e) {
            throw new GuliException(MyHttpStatus.TOKEN_EXPIRE.getCode(), MyHttpStatus.TOKEN_EXPIRE.getDescription());
        }
    }
}
