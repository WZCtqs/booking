package com.zih.booking.service.impl;

import com.zih.booking.model.SysMessage;
import com.zih.booking.dao.SysMessageMapper;
import com.zih.booking.service.SysMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 消息提醒 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-04-08
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {

    @Resource
    SysMessageMapper sysMessageMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<SysMessage> getMsgPageByClientId(String clientId,String messageTitle, int pageNo, int pageSize) {

        return sysMessageMapper.getMsgPageByClientId(clientId, messageTitle, (pageNo-1)*pageSize, pageSize, pageNo * pageSize);
    }

    @Override
    public int getMsgPageByClientIdCount(String clientId,String messageTitle) {
        return sysMessageMapper.getMsgPageByClientIdCount(clientId, messageTitle);
    }

    @Override
    public int getNotReadCount(String clientId) {
        Integer count = (Integer) redisTemplate.opsForValue().get("undeal:"+clientId);
        if(count != null){
            return count;
        }else {
            count = sysMessageMapper.getNotReadCount(clientId);
            redisTemplate.opsForValue().set("undeal:"+clientId, count, 1, TimeUnit.HOURS);
            return count;
        }
    }
}
