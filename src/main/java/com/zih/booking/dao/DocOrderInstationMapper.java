package com.zih.booking.dao;

import com.zih.booking.model.DocOrderInstation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2021-02-03
 */
public interface DocOrderInstationMapper extends BaseMapper<DocOrderInstation> {

    List<DocOrderInstation> getDocOrderInstByConNo(@Param("containerNo")String containerNo, @Param("classBh")String classBh);
}
