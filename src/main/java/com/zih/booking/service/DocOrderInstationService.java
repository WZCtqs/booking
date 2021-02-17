package com.zih.booking.service;

import com.zih.booking.model.DocOrderInstation;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wsl123
 * @since 2021-02-03
 */
public interface DocOrderInstationService extends IService<DocOrderInstation> {
    List<DocOrderInstation> getDocOrderInstByConNo(String containerNo, String classBh);
}
