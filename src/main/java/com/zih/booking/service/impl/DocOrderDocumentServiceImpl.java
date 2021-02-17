package com.zih.booking.service.impl;

import com.zih.booking.model.DocOrderDocument;
import com.zih.booking.dao.DocOrderDocumentMapper;
import com.zih.booking.request.CustomsRequest;
import com.zih.booking.response.CustomsResponse;
import com.zih.booking.service.DocOrderDocumentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 单证文件表 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-01-07
 */
@Service
public class DocOrderDocumentServiceImpl extends ServiceImpl<DocOrderDocumentMapper, DocOrderDocument> implements DocOrderDocumentService {

    @Autowired
    private DocOrderDocumentMapper docOrderDocumentMapper;

    @Override
    public List<CustomsResponse> getCustomsDataList(CustomsRequest customsRequest) {
        return docOrderDocumentMapper.getCustomsDataList(customsRequest);
    }

    @Override
    public int deleteByOrderIdAndFileTypeKey(String orderId, String fileTypeKey) {
        return docOrderDocumentMapper.deleteByOrderIdAndFileTypeKey(orderId, fileTypeKey);
    }

    @Override
    public DocOrderDocument getDocOrderDocumentById(Long id) {
        return docOrderDocumentMapper.getDocOrderDocumentById(id);
    }
}
