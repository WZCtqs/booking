package com.zih.booking.system.enums;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @Description: 国际化 语言枚举类
 * @Author: shahy
 * @Date: 2020/1/6
 */
@Getter
@ToString
public enum LanguageEnum {

    /**
     * 美式英文
     */
    LANGUAGE_EN_US("en"),
    /**
     * 简体中文
     */
    LANGUAGE_ZH_CN("zh");

    private String language;

    private LanguageEnum(String language){
        this.language = language;
    }

    /**
     * 获取指定语言类型(如果没有对应的语言类型,则返回中文)
     *
     * @param language 语言类型
     * @return
     */
    public static String getLanguageType(String language){
        if (StringUtils.isEmpty(language)) {
            return LANGUAGE_ZH_CN.language;
        }
        for (LanguageEnum languageEnum : LanguageEnum.values()) {
            if (languageEnum.language.equalsIgnoreCase(language)) {
                return languageEnum.language;
            }
        }
        return LANGUAGE_ZH_CN.language;
    }

}
