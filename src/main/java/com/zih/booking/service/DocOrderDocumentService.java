package com.zih.booking.service;

import com.zih.booking.model.DocOrderDocument;
import com.baomidou.mybatisplus.service.IService;
import com.zih.booking.request.CustomsRequest;
import com.zih.booking.response.CustomsResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 单证文件表 服务类
 * </p>
 *
 * @author wsl123
 * @since 2020-01-07
 */
public interface DocOrderDocumentService extends IService<DocOrderDocument> {

    List<CustomsResponse> getCustomsDataList(CustomsRequest customsRequest);

    public int deleteByOrderIdAndFileTypeKey(String orderId, String fileTypeKey);

    DocOrderDocument getDocOrderDocumentById(Long id);
}
