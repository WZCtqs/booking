package com.zih.booking.service;

import com.zih.booking.model.SysMessage;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息提醒 服务类
 * </p>
 *
 * @author wsl123
 * @since 2020-04-08
 */
public interface SysMessageService extends IService<SysMessage> {

    List<SysMessage> getMsgPageByClientId(String clientId, String messageTitle, int pageNo, int pageSize);
    int getMsgPageByClientIdCount(String clientId, String messageTitle);
    int getNotReadCount(String clientId);
}
