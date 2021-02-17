package com.zih.booking.dao;

import com.zih.booking.model.TrackGoodsStatus;
import com.zih.booking.model.TrackTrain;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.vo.EmaiMqVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 运踪_班列站到站 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-01-14
 */
public interface TrackTrainMapper extends BaseMapper<TrackTrain> {
    //根据classId查询班列运踪
    public List<TrackTrain> selectTtList(@Param("classId")String classId, @Param("actualDate")String actualDate, @Param("batchTime")String batchTime);
    //根据tgs查询班列运踪
    public List<TrackTrain> selectTrainListByTgs(TrackGoodsStatus tgs);
    //获取发送邮件的邮箱
    public EmaiMqVo  selectEmail(EmaiMqVo emaiMqVo);
}
