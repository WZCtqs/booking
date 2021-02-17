package com.zih.booking.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.model.DocOrderDocument;
import com.zih.booking.request.CustomsRequest;
import com.zih.booking.response.CustomsResponse;
import com.zih.booking.response.DocNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 单证文件表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-01-07
 */
@Repository
public interface DocOrderDocumentMapper extends BaseMapper<DocOrderDocument> {
    List<DocNode> selectNodeList(@Param("orderId")String orderId, @Param("fileTypeKey") List<String>  fileTypeKey);

    List<DocNode> selectNewNodeList(@Param("orderId")String orderId, @Param("fileTypeKey") List<String>  fileTypeKey);

    List<CustomsResponse> getCustomsDataList(CustomsRequest customsRequest);

    public int deleteByOrderIdAndFileTypeKey(@Param("orderId") String orderId,
                                             @Param("fileTypeKey") String fileTypeKey);

    DocOrderDocument getDocOrderDocumentById(Long id);
}
