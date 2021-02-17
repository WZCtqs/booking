package com.zih.booking.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 运踪_去回程站到门/门到站
 * </p>
 *
 * @author wsl123
 * @since 2020-01-15
 */
@TableName("track_station_to_door")
public class TrackStationToDoor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 订舱草稿时间
     */
    @TableField("draft_time")
    private String draftTime;
    /**
     * 取消托书时间
     */
    @TableField("cancel_book_time")
    private String cancelBookTime;
    /**
     * 待审核时间
     */
    @TableField("check_pending_time")
    private String checkPendingTime;
    /**
     * 审核结果（通过/失败）时间
     */
    @TableField("check_result_time")
    private String checkResultTime;
    /**
     * 审核结果0通过1失败
     */
    @TableField("check_result")
    private Integer checkResult;
    /**
     * 审核失败原因
     */
    @TableField("check_fail_reason")
    private String checkFailReason;
    /**
     * 转待审核时间
     */
    @TableField("check_again_time")
    private String checkAgainTime;
    /**
     * 转待审核结果（通过/失败）时间
     */
    @TableField("check_again_result_time")
    private String checkAgainResultTime;
    /**
     * 转待审核结果0通过1失败
     */
    @TableField("check_again_result")
    private Integer checkAgainResult;
    /**
     * 转待审核失败原因
     */
    @TableField("check_again_fail_reason")
    private String checkAgainFailReason;
    /**
     * 放箱时间
     */
    @TableField("release_box_time")
    private String releaseBoxTime;
    /**
     * 派车时间
     */
    @TableField("send_car_time")
    private String sendCarTime;
    /**
     * 司机信息
     */
    @TableField("driver_information")
    private String driverInformation;
    /**
     * 提箱时间(门到站)
     */
    @TableField("carry_box_time")
    private String carryBoxTime;
    /**
     * 提货时间
     */
    @TableField("carry_goods_time")
    private String carryGoodsTime;
    /**
     * 预计送达时间（门到站，运输中）
     */
    @TableField("expect_arrive_time")
    private String expectArriveTime;
    /**
     * 送达时间(门到站)
     */
    @TableField("arrive_time")
    private String arriveTime;
    /**
     * 入仓时间
     */
    @TableField("in_store_time")
    private String inStoreTime;
    /**
     * 进站时间
     */
    @TableField("in_station_time")
    private String inStationTime;
    /**
     * 单据提供时间（/报关|清关）
     */
    @TableField("bills_provide_time")
    private String billsProvideTime;
    /**
     * 问题沟通时间（沟通中）
     */
    @TableField("problem_communicate_time")
    private String problemCommunicateTime;
    /**
     * 草单确认时间
     */
    @TableField("straw_sure_time")
    private String strawSureTime;
    /**
     * 正本提供时间
     */
    @TableField("original_provide_time")
    private String originalProvideTime;
    /**
     * 申报时间
     */
    @TableField("apply_time")
    private String applyTime;
    /**
     * 缴税时间（回程）
     */
    @TableField("pay_tax_time")
    private String payTaxTime;
    /**
     * 布控时间
     */
    @TableField("layout_time")
    private String layoutTime;
    /**
     * 布控原因
     */
    @TableField("layout_reason")
    private String layoutReason;
    /**
     * 重量异常（回程）
     */
    @TableField("weight_abnormal")
    private String weightAbnormal;
    /**
     * 查验时间（回程）
     */
    @TableField("inspection_time")
    private String inspectionTime;
    /**
     * 查验原因（回程）
     */
    @TableField("inspection_reason")
    private String inspectionReason;
    /**
     * 放行时间（报关|清关/）
     */
    @TableField("release_time")
    private String releaseTime;
    /**
     * 单证提供时间(回程)
     */
    @TableField("document_provide_time")
    private String documentProvideTime;
    /**
     * 单证审核结果（回程）
     */
    @TableField("document_check_result")
    private String documentCheckResult;
    /**
     * 班列发车时间
     */
    @TableField("train_depart_time")
    private String trainDepartTime;
    /**
     * 提箱资料发送时间（/整柜）
     */
    @TableField("carry_box_information_time")
    private String carryBoxInformationTime;
    /**
     * 提箱时间（站到门）
     */
    @TableField("carry_container_time")
    private String carryContainerTime;
    /**
     * 司机信息（回程）
     */
    @TableField("driver_news")
    private String driverNews;
    /**
     * 预计送达时间（站到门）
     */
    @TableField("expect_reach_time")
    private String expectReachTime;
    /**
     * 送达时间（站到门）
     */
    @TableField("reach_time")
    private String reachTime;
    /**
     * 签收时间
     */
    @TableField("sign_time")
    private String signTime;
    /**
     * 还箱时间（整柜/)
     */
    @TableField("return_box_time")
    private String returnBoxTime;
    /**
     * 提货时间（回程拼箱中心站到堆场）
     */
    @TableField("get_cargo_time")
    private String getCargoTime;
    /**
     * 拆箱完成时间（/拼箱）
     */
    @TableField("devanning_time")
    private String devanningTime;
    /**
     * 预计可提货时间
     */
    @TableField("expect_carry_cargo_time")
    private String expectCarryCargoTime;
    /**
     * 提货时间
     */
    @TableField("carry_cargo_time")
    private String carryCargoTime;
    /**
     * 司机信息
     */
    @TableField("driver_message")
    private String driverMessage;
    /**
     * 预计送达时间
     */
    @TableField("except_come_time")
    private String exceptComeTime;
    /**
     * 送达时间
     */
    @TableField("come_time")
    private String comeTime;
    /**
     * 签收时间（拼箱/）
     */
    @TableField("receive_time")
    private String receiveTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志0正常1删除
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDraftTime() {
        return draftTime;
    }

    public void setDraftTime(String draftTime) {
        this.draftTime = draftTime;
    }

    public String getCancelBookTime() {
        return cancelBookTime;
    }

    public void setCancelBookTime(String cancelBookTime) {
        this.cancelBookTime = cancelBookTime;
    }

    public String getCheckPendingTime() {
        return checkPendingTime;
    }

    public void setCheckPendingTime(String checkPendingTime) {
        this.checkPendingTime = checkPendingTime;
    }

    public String getCheckResultTime() {
        return checkResultTime;
    }

    public void setCheckResultTime(String checkResultTime) {
        this.checkResultTime = checkResultTime;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckFailReason() {
        return checkFailReason;
    }

    public void setCheckFailReason(String checkFailReason) {
        this.checkFailReason = checkFailReason;
    }

    public String getCheckAgainTime() {
        return checkAgainTime;
    }

    public void setCheckAgainTime(String checkAgainTime) {
        this.checkAgainTime = checkAgainTime;
    }

    public String getCheckAgainResultTime() {
        return checkAgainResultTime;
    }

    public void setCheckAgainResultTime(String checkAgainResultTime) {
        this.checkAgainResultTime = checkAgainResultTime;
    }

    public Integer getCheckAgainResult() {
        return checkAgainResult;
    }

    public void setCheckAgainResult(Integer checkAgainResult) {
        this.checkAgainResult = checkAgainResult;
    }

    public String getCheckAgainFailReason() {
        return checkAgainFailReason;
    }

    public void setCheckAgainFailReason(String checkAgainFailReason) {
        this.checkAgainFailReason = checkAgainFailReason;
    }

    public String getReleaseBoxTime() {
        return releaseBoxTime;
    }

    public void setReleaseBoxTime(String releaseBoxTime) {
        this.releaseBoxTime = releaseBoxTime;
    }

    public String getSendCarTime() {
        return sendCarTime;
    }

    public void setSendCarTime(String sendCarTime) {
        this.sendCarTime = sendCarTime;
    }

    public String getDriverInformation() {
        return driverInformation;
    }

    public void setDriverInformation(String driverInformation) {
        this.driverInformation = driverInformation;
    }

    public String getCarryBoxTime() {
        return carryBoxTime;
    }

    public void setCarryBoxTime(String carryBoxTime) {
        this.carryBoxTime = carryBoxTime;
    }

    public String getCarryGoodsTime() {
        return carryGoodsTime;
    }

    public void setCarryGoodsTime(String carryGoodsTime) {
        this.carryGoodsTime = carryGoodsTime;
    }

    public String getExpectArriveTime() {
        return expectArriveTime;
    }

    public void setExpectArriveTime(String expectArriveTime) {
        this.expectArriveTime = expectArriveTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getInStoreTime() {
        return inStoreTime;
    }

    public void setInStoreTime(String inStoreTime) {
        this.inStoreTime = inStoreTime;
    }

    public String getInStationTime() {
        return inStationTime;
    }

    public void setInStationTime(String inStationTime) {
        this.inStationTime = inStationTime;
    }

    public String getBillsProvideTime() {
        return billsProvideTime;
    }

    public void setBillsProvideTime(String billsProvideTime) {
        this.billsProvideTime = billsProvideTime;
    }

    public String getProblemCommunicateTime() {
        return problemCommunicateTime;
    }

    public void setProblemCommunicateTime(String problemCommunicateTime) {
        this.problemCommunicateTime = problemCommunicateTime;
    }

    public String getStrawSureTime() {
        return strawSureTime;
    }

    public void setStrawSureTime(String strawSureTime) {
        this.strawSureTime = strawSureTime;
    }

    public String getOriginalProvideTime() {
        return originalProvideTime;
    }

    public void setOriginalProvideTime(String originalProvideTime) {
        this.originalProvideTime = originalProvideTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getPayTaxTime() {
        return payTaxTime;
    }

    public void setPayTaxTime(String payTaxTime) {
        this.payTaxTime = payTaxTime;
    }

    public String getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(String layoutTime) {
        this.layoutTime = layoutTime;
    }

    public String getLayoutReason() {
        return layoutReason;
    }

    public void setLayoutReason(String layoutReason) {
        this.layoutReason = layoutReason;
    }

    public String getWeightAbnormal() {
        return weightAbnormal;
    }

    public void setWeightAbnormal(String weightAbnormal) {
        this.weightAbnormal = weightAbnormal;
    }

    public String getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getInspectionReason() {
        return inspectionReason;
    }

    public void setInspectionReason(String inspectionReason) {
        this.inspectionReason = inspectionReason;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDocumentProvideTime() {
        return documentProvideTime;
    }

    public void setDocumentProvideTime(String documentProvideTime) {
        this.documentProvideTime = documentProvideTime;
    }

    public String getDocumentCheckResult() {
        return documentCheckResult;
    }

    public void setDocumentCheckResult(String documentCheckResult) {
        this.documentCheckResult = documentCheckResult;
    }

    public String getTrainDepartTime() {
        return trainDepartTime;
    }

    public void setTrainDepartTime(String trainDepartTime) {
        this.trainDepartTime = trainDepartTime;
    }

    public String getCarryBoxInformationTime() {
        return carryBoxInformationTime;
    }

    public void setCarryBoxInformationTime(String carryBoxInformationTime) {
        this.carryBoxInformationTime = carryBoxInformationTime;
    }

    public String getCarryContainerTime() {
        return carryContainerTime;
    }

    public void setCarryContainerTime(String carryContainerTime) {
        this.carryContainerTime = carryContainerTime;
    }

    public String getDriverNews() {
        return driverNews;
    }

    public void setDriverNews(String driverNews) {
        this.driverNews = driverNews;
    }

    public String getExpectReachTime() {
        return expectReachTime;
    }

    public void setExpectReachTime(String expectReachTime) {
        this.expectReachTime = expectReachTime;
    }

    public String getReachTime() {
        return reachTime;
    }

    public void setReachTime(String reachTime) {
        this.reachTime = reachTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getReturnBoxTime() {
        return returnBoxTime;
    }

    public void setReturnBoxTime(String returnBoxTime) {
        this.returnBoxTime = returnBoxTime;
    }

    public String getGetCargoTime() {
        return getCargoTime;
    }

    public void setGetCargoTime(String getCargoTime) {
        this.getCargoTime = getCargoTime;
    }

    public String getDevanningTime() {
        return devanningTime;
    }

    public void setDevanningTime(String devanningTime) {
        this.devanningTime = devanningTime;
    }

    public String getExpectCarryCargoTime() {
        return expectCarryCargoTime;
    }

    public void setExpectCarryCargoTime(String expectCarryCargoTime) {
        this.expectCarryCargoTime = expectCarryCargoTime;
    }

    public String getCarryCargoTime() {
        return carryCargoTime;
    }

    public void setCarryCargoTime(String carryCargoTime) {
        this.carryCargoTime = carryCargoTime;
    }

    public String getDriverMessage() {
        return driverMessage;
    }

    public void setDriverMessage(String driverMessage) {
        this.driverMessage = driverMessage;
    }

    public String getExceptComeTime() {
        return exceptComeTime;
    }

    public void setExceptComeTime(String exceptComeTime) {
        this.exceptComeTime = exceptComeTime;
    }

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TrackStationToDoor{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", draftTime=" + draftTime +
        ", cancelBookTime=" + cancelBookTime +
        ", checkPendingTime=" + checkPendingTime +
        ", checkResultTime=" + checkResultTime +
        ", checkResult=" + checkResult +
        ", checkFailReason=" + checkFailReason +
        ", checkAgainTime=" + checkAgainTime +
        ", checkAgainResultTime=" + checkAgainResultTime +
        ", checkAgainResult=" + checkAgainResult +
        ", checkAgainFailReason=" + checkAgainFailReason +
        ", releaseBoxTime=" + releaseBoxTime +
        ", sendCarTime=" + sendCarTime +
        ", driverInformation=" + driverInformation +
        ", carryBoxTime=" + carryBoxTime +
        ", carryGoodsTime=" + carryGoodsTime +
        ", expectArriveTime=" + expectArriveTime +
        ", arriveTime=" + arriveTime +
        ", inStoreTime=" + inStoreTime +
        ", inStationTime=" + inStationTime +
        ", billsProvideTime=" + billsProvideTime +
        ", problemCommunicateTime=" + problemCommunicateTime +
        ", strawSureTime=" + strawSureTime +
        ", originalProvideTime=" + originalProvideTime +
        ", applyTime=" + applyTime +
        ", payTaxTime=" + payTaxTime +
        ", layoutTime=" + layoutTime +
        ", layoutReason=" + layoutReason +
        ", weightAbnormal=" + weightAbnormal +
        ", inspectionTime=" + inspectionTime +
        ", inspectionReason=" + inspectionReason +
        ", releaseTime=" + releaseTime +
        ", documentProvideTime=" + documentProvideTime +
        ", documentCheckResult=" + documentCheckResult +
        ", trainDepartTime=" + trainDepartTime +
        ", carryBoxInformationTime=" + carryBoxInformationTime +
        ", carryContainerTime=" + carryContainerTime +
        ", driverNews=" + driverNews +
        ", expectReachTime=" + expectReachTime +
        ", reachTime=" + reachTime +
        ", signTime=" + signTime +
        ", returnBoxTime=" + returnBoxTime +
        ", getCargoTime=" + getCargoTime +
        ", devanningTime=" + devanningTime +
        ", expectCarryCargoTime=" + expectCarryCargoTime +
        ", carryCargoTime=" + carryCargoTime +
        ", driverMessage=" + driverMessage +
        ", exceptComeTime=" + exceptComeTime +
        ", comeTime=" + comeTime +
        ", receiveTime=" + receiveTime +
        ", remark=" + remark +
        ", delFlag=" + delFlag +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
