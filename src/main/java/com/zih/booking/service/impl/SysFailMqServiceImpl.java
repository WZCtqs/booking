package com.zih.booking.service.impl;

import com.zih.booking.model.SysFailMq;
import com.zih.booking.dao.SysFailMqMapper;
import com.zih.booking.service.SysFailMqService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息队列发送失败消息 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-12-09
 */
@Service
public class SysFailMqServiceImpl extends ServiceImpl<SysFailMqMapper, SysFailMq> implements SysFailMqService {

}
