package com.zih.booking.service.impl;

import com.zih.booking.model.DocOrderInstation;
import com.zih.booking.dao.DocOrderInstationMapper;
import com.zih.booking.service.DocOrderInstationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2021-02-03
 */
@Service
public class DocOrderInstationServiceImpl extends ServiceImpl<DocOrderInstationMapper, DocOrderInstation> implements DocOrderInstationService {

    @Resource
    DocOrderInstationMapper instationMapper;

    @Override
    public List<DocOrderInstation> getDocOrderInstByConNo(String containerNo, String classBh) {
        return instationMapper.getDocOrderInstByConNo(containerNo, classBh);
    }
}
