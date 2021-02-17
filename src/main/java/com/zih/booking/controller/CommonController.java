package com.zih.booking.controller;


import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.vo.ApiResultI18n;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequestMapping("/common")
@RestController
public class CommonController {

    /**
     * 未授权跳转方法
     * @return
     */
    @RequestMapping("/unauth")
    public ApiResultI18n unauth(){
        SecurityUtils.getSubject().logout();
        return ApiResultI18n.failure(ResultStatusCode.UNAUTHO_ERROR,null);
    }

    /**
     * 被踢出后跳转方法
     * @return
     */
    @RequestMapping("/kickout")
    public ApiResultI18n kickout(){
        return ApiResultI18n.failure(ResultStatusCode.INVALID_TOKEN,null);
    }

    /**
     * 国际化测试
     * @return
     */
    @RequestMapping("/international")
    public ApiResultI18n international(String language){

        return ApiResultI18n.failure(language);
    }
}
