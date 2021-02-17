package com.zih.booking.dao;

import com.zih.booking.model.BaseGoodsnote;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 特殊单证物品表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-31
 */
@Repository
public interface BaseGoodsnoteMapper extends BaseMapper<BaseGoodsnote> {
    List<BaseGoodsnote> selectBaseGoodsnoteListByName(@Param("eastandwest") String eastandwest, @Param("goodsname")String goodsname);
    List<BaseGoodsnote> selectBaseGoodsnoteListByOrderHs(@Param("eastandwest")String eastandwest,@Param("inhs")String inhs);
}
