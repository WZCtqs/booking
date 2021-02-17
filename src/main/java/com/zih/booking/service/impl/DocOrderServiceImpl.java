package com.zih.booking.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zih.booking.dao.DocOrderMapper;
import com.zih.booking.model.DocOrder;
import com.zih.booking.service.DocOrderService;
import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Author : wangzhichao
 * @Date: 2020-12-19 09:04
 */
@Service
public class DocOrderServiceImpl extends ServiceImpl<DocOrderMapper, DocOrder> implements DocOrderService {
}
