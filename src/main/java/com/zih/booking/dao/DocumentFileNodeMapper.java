package com.zih.booking.dao;

import com.zih.booking.model.DocumentFileNode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-05
 */
@Repository
public interface DocumentFileNodeMapper extends BaseMapper<DocumentFileNode> {
List<DocumentFileNode> selectNodeList(@Param("orderNumber")String orderNumber );
}
