package com.zih.booking.dao;

import com.zih.booking.model.SysMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息提醒 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-04-08
 */
public interface SysMessageMapper extends BaseMapper<SysMessage> {

    List<SysMessage> getMsgPageByClientId(@Param("clientId") String clientId,
                                          @Param("messageTitle") String messageTitle,
                                          @Param("pageNo") int pageNo,
                                          @Param("pageSize") int pageSize,
                                          @Param("pageSize2") int pageSize2);

    int getMsgPageByClientIdCount(@Param("clientId") String clientId,
                                  @Param("messageTitle") String messageTitle);

    int getNotReadCount(String clientId);
}
