package com.zih.booking.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zih.booking.request.BaseRequest;
import com.zih.booking.request.LoginRequest;
import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.UUIDUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "登录",notes= "账号密码验证码登录")
    @PostMapping(value = "/login")
    public ApiResultI18n login(LoginRequest loginRequest) {
        String codeRedis = (String) redisTemplate.opsForValue().get(loginRequest.getCodeToken());

        if (!(StringUtils.isNotEmpty(codeRedis) && codeRedis.equals(loginRequest.getVerifyCode()))){
            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_ERROR,loginRequest.getLanguage());
        }
        return ApiResultI18n.success(loginRequest.getLanguage());
    }


    @ApiOperation(value = "获取验证码",notes= "获取验证码的接口")
    @GetMapping(value = "/getCode")
    public ApiResultI18n generateVerificationCode(BaseRequest baseRequest) {
        Map<String, Object> map = new HashMap<>();
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(text);
        outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_GENERATE_ERROR,baseRequest.getLanguage());
        }
        //base64图片
        BASE64Encoder encoder = new BASE64Encoder();
        String imageStr=encoder.encode(outputStream.toByteArray());
        map.put("img",imageStr);
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        String codeToken = UUIDUtils.getStringRandom(16);
        redisTemplate.opsForValue().set(codeToken, text, 10, TimeUnit.MINUTES);
        map.put("codeToken", codeToken);
        return ApiResultI18n.success(map,baseRequest.getLanguage());
    }
}
