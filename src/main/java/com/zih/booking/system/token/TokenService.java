package com.zih.booking.system.token;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.KhUser;
import com.zih.booking.service.KhUserService;
import com.zih.booking.system.config.Consts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Slf4j
@Component
public class TokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private KhUserService khUserService;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        //     log.info("token="+token);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = null;
            try {
                claims = parseToken(token);
            } catch (Exception e) {
                log.info(e.getMessage(), e);
                return null;
            }
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(Consts.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            LoginUser user = JSON.parseObject(redisCache.getCacheObject(userKey), LoginUser.class);
            return user;
        }
        return null;
    }

    //获取登录人id
    public String getClientId(HttpServletRequest request) {
        LoginUser user = getLoginUser(request);
        String clientId = "1";
        if (!ObjectUtils.isEmpty(user)) {
            clientId = user.getUserType().equals("0") ?
                    user.getBusiClients().getClientId() : user.getKhUser().getId().toString();
        }
        return clientId;
    }

    //获取登录人及其子账号所有id
    public List<String> getClientIds(HttpServletRequest request) {
        LoginUser user = getLoginUser(request);
        List<String> clientIds = new ArrayList<>();
        if (!ObjectUtils.isEmpty(user)) {
            clientIds.add(getClientId(request));//登录人id
            if (user.getUserType().equals("0")) {
                List<KhUser> khUsers = khUserService.selectList(new EntityWrapper<KhUser>().eq("client_id", user.getBusiClients().getClientId()));
                for (KhUser khUser : khUsers) {
                    clientIds.add(khUser.getId() + "");//登录人的所有子客户id
                }
            }
        }
        return clientIds;
    }

    //获取登录子账号id
    public String getKhId(HttpServletRequest request){
        LoginUser user = getLoginUser(request);
        String khId=null;
        if(user!=null&&user.getKhUser()!=null){
            khId = String.valueOf(user.getKhUser().getId());
        }
        return khId;
    }

    //获取登录子账号客户id
    public String getKhClientId(HttpServletRequest request){
        LoginUser user = getLoginUser(request);
        String clientId=null;
        if(user!=null&&user.getKhUser()!=null){
            clientId = user.getKhUser().getClientId();
        }
        return clientId;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if ((!ObjectUtils.isEmpty(loginUser)) && StringUtils.isNotEmpty(loginUser.getToken())) {
            String userKey = getTokenKey(loginUser.getToken());
            redisCache.setCacheObject(userKey, JSON.toJSONString(loginUser));
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            String token = loginUser.getToken();
            loginUser.setToken(token);
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, JSON.toJSONString(loginUser), expireTime, TimeUnit.MINUTES);
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Consts.TOKEN_PREFIX)) {
            token = token.replace(Consts.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Consts.LOGIN_TOKEN_KEY + uuid;
    }
}
