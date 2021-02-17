package com.zih.booking.system.shiro;

import com.alibaba.fastjson.JSON;
import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.token.LoginUser;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author shahy
 */
@Slf4j
public class TokenControlFilter extends UserFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS");
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        LoginUser loginUser = tokenService.getLoginUser((HttpServletRequest) request);
        if ((!ObjectUtils.isEmpty(loginUser)) && StringUtils.isNotEmpty(loginUser.getUserType())) {
            tokenService.verifyToken(loginUser);
            return true;
        }else {
            out(response, ApiResultI18n.failure(ResultStatusCode.INVALID_TOKEN,null));
            return false;
        }
//        Subject subject = getSubject(request, response);
//        if(!subject.isAuthenticated() && !subject.isRemembered()) {
//            out(response, ApiResultI18n.failure(ResultStatusCode.INVALID_TOKEN,null));
//            return false;
//        }
//        return true;
    }

    private void out(ServletResponse response, ApiResultI18n apiResultI18n) {
        try {
            HttpServletResponse res = (HttpServletResponse)response;
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.addHeader("Access-Control-Allow-Headers",
                    "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json; charset=utf-8");
            PrintWriter out = res.getWriter();
            out.println(JSON.toJSON(apiResultI18n));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.debug("TokenControlFilter.class 输出JSON异常，可以忽略。");
        }
    }
}
