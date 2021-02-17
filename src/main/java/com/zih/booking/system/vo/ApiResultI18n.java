package com.zih.booking.system.vo;

import com.zih.booking.system.config.I18nMessageUtil;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.enums.ResultStatusCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Description: 多语言, 国际化接口返回结果封装
 * @Author: shahy
 * @Date: 2020/1/6
 */
@Slf4j
@Data
public class ApiResultI18n implements Serializable {

    private static final long serialVersionUID = 4518290031778225230L;

    /**
     * 返回码，0 正常
     */
    private int code = 0;

    /**
     * 返回信息
     */
    private String msg = "成功";

    /**
     * 返回数据
     */
    private Object data;

    /**
     * api 返回结果
     */
    private ApiResultI18n() {}

    /**
     * api 返回结果,区分多语言
     *
     * @param language 语言类型,eg: en_us 表示美式英文
     */
    public ApiResultI18n(String language){
        this.code = ResultStatusCode.SUCCESS.getCode();
        language = LanguageEnum.getLanguageType(language);
        try {
            this.msg = I18nMessageUtil.getMessage(language,ResultStatusCode.SUCCESS.getMsg(),"SUCCESS");
        } catch (IOException e) {
            this.msg = "SUCCESS";
        }
    }

    /**
     * 获取成功状态结果,区分多语言(默认简体中文)
     *
     * @param language 语言类型,eg: en_us 表示美式英文
     * @return
     */
    public static ApiResultI18n success(String language) {
        return success(null, language);
    }

    /**
     * 获取成功状态结果,区分多语言(默认简体中文)
     *
     * @param data 返回数据
     * @param language 语言类型,eg: en_us 表示美式英文
     * @return
     */
    public static ApiResultI18n success(Object data, String language) {
        ApiResultI18n result = new ApiResultI18n(language);
        result.setData(data);
        return result;
    }

    /**
     * 获取失败状态结果,区分多语言(默认简体中文)
     *
     * @param language 语言类型,eg: en_us 表示美式英文
     * @return
     */
    public static ApiResultI18n failure(String language) {
        return failure(ResultStatusCode.FAIL.getCode(), ResultStatusCode.FAIL.getMsg(), null, language);
    }

    /**
     * 获取失败结果,区分多语言(默认中文)
     *
     * @param resultStatusCode 返回码
     * @param language 语言类型
     * @return
     */
    public static ApiResultI18n failure(ResultStatusCode resultStatusCode, String language) {
        return failure(resultStatusCode.getCode(), resultStatusCode.getMsg(), null, language);
    }

    /**
     * 获取失败状态结果,区分多语言(默认中文)
     *
     * @param code 返回状态码
     * @param msg 错误信息
     * @param language 语言类型,eg: en 表示英文
     * @return
     */
    public static ApiResultI18n failure(int code, String msg, String language) {
        return failure(code ,msg, null, language);
    }

    /**
     * 获取失败返回结果,区分多语言(默认中文)
     *
     * @param code 错误码
     * @param msg 错误信息
     * @param data 返回结果
     * @param language 语言类型,eg: en 表示英文
     * @return
     */
    public static ApiResultI18n failure(int code, String msg, Object data, String language) {
        ApiResultI18n result = new ApiResultI18n(language);
        language = LanguageEnum.getLanguageType(language);
        try {
            msg = I18nMessageUtil.getMessage(language, msg, msg);
        } catch (IOException e) {
            log.info(e.getMessage(),e);
            msg = "Error";
        }
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        if (data instanceof String) {
            String m = (String) data;
            if (!m.matches("^.*error$")) {
                m += "error";
            }
        }
        return result;
    }
}

