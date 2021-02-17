package com.zih.booking.service.impl;

import com.zih.booking.model.KhUser;
import com.zih.booking.dao.KhUserMapper;
import com.zih.booking.service.KhUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端用户表 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-04-15
 */
@Service
public class KhUserServiceImpl extends ServiceImpl<KhUserMapper, KhUser> implements KhUserService {

}
