package com.zih.booking.settlementDao;

import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.BusiTaizangInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.request.SettlementRequest;
import com.zih.booking.response.SettlementResponse;
import com.zih.booking.vo.TaiZangDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-03-17
 */
@Repository
public interface BusiTaizangInfoMapper extends BaseMapper<BusiTaizangInfo> {
    TaiZangDetail getByOrderId(String orderId);//费用单详情
    List<Map<String, String>> getAllExports(SettlementRequest settlementRequest);//导出的列表SettlementRequest settlementRequest
    List<SettlementResponse> getSettlementList(Page page, SettlementRequest settlementRequest);
}
