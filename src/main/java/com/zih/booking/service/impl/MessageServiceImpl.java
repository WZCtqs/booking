package com.zih.booking.service.impl;

import com.zih.booking.model.Message;
import com.zih.booking.dao.MessageMapper;
import com.zih.booking.service.MessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-03-05
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
