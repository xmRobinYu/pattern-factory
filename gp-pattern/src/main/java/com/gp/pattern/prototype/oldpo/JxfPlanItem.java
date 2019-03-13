/*
* @(#)JxfPlanItem.java
*
* Copyright 2017-2018, Inc. All rights reserved.
*
*/
package com.gp.pattern.prototype.oldpo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class <code>JxfPlanItem</code> is the root of the class hierarchy.
 * Every class has <code>JxfPlanItem</code> as a superclass. All objects,
 * including arrays, implement the methods of this class.
 *
 * @author yuxb
 * @version 2018-08-02
 * @see Class
 */
public class JxfPlanItem implements Serializable {


    private String id;


    private String merchantId;


    private String bankAccountNumber;


    private Integer planIndex;


    private String planId;


    private String groupId;


    private Integer itemType;


    private String planTime;


    private String orderId;


    private String withDrawalsNo;


    private BigDecimal planMoney;


    private BigDecimal costMoney;


    private String acqCode;


    private String executeDesc;


    private String qryDesc;


    private Integer failTimes;


    private String category;


    private String categoryName;


    private String createTime;


    private String updateTime;


    private Integer status;


    private Integer withDrawalsFlag;


    private Integer showFlag;


    private Integer qryTimes;


    private Integer lastQryStatus;


    private String lastOrderId;


    private String lastWithDrawalsNo;

    private BigDecimal discountAmount;

    private Integer failType;

    private Integer qryAgain;

    private BigDecimal channelCostFee;

    private String realExecuteTime;

    private BigDecimal realCostRate;

    private BigDecimal realCostFee;


    public JxfPlanItem(Integer planIndex) {
        this.id = "1212saFSADF" + planIndex;
        this.merchantId = "1212" + planIndex;
        this.bankAccountNumber = "1212123" + planIndex;
        this.planIndex = planIndex;
        this.planId = "12121adsfasf";
        this.groupId = "12121adsfasf";
        this.itemType = 1;
        this.planTime = "2019-03-12 15:25:12";
        this.orderId = "12121adsfasf";
        this.withDrawalsNo = "12121adsfasf";
        this.planMoney = new BigDecimal(100).add(new BigDecimal(planIndex));
        this.costMoney = new BigDecimal(12);
        this.acqCode = "8097";
        this.executeDesc = "";
        this.qryDesc = "";
        this.failTimes = 0;
        this.category = "12121adsfasf";
        this.categoryName = "12121adsfasf";
        this.createTime = "12121adsfasf";
        this.updateTime = "12121adsfasf";
        this.status = 1;
        this.withDrawalsFlag = 1;
        this.showFlag = 1;
        this.qryTimes = 1;
        this.lastQryStatus = 1;
        this.lastOrderId = "12121adsfasf";
        this.lastWithDrawalsNo = "12121adsfasf";
        this.discountAmount = new BigDecimal(1);
        this.failType = 1;
        this.qryAgain = 1;
        this.channelCostFee = new BigDecimal(0);
        this.realExecuteTime = "2019-03-11";
        this.realCostRate = new BigDecimal(0.0085);
        this.realCostFee = new BigDecimal(1);
    }

    public BigDecimal getChannelCostFee() {
        return channelCostFee;
    }

    public void setChannelCostFee(BigDecimal channelCostFee) {
        this.channelCostFee = channelCostFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }


    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    public Integer getPlanIndex() {
        return planIndex;
    }

    public void setPlanIndex(Integer planIndex) {
        this.planIndex = planIndex;
    }


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }


    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getWithDrawalsNo() {
        return withDrawalsNo;
    }

    public void setWithDrawalsNo(String withDrawalsNo) {
        this.withDrawalsNo = withDrawalsNo;
    }


    public BigDecimal getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(BigDecimal planMoney) {
        this.planMoney = planMoney;
    }


    public BigDecimal getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(BigDecimal costMoney) {
        this.costMoney = costMoney;
    }


    public String getAcqCode() {
        return acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }


    public String getExecuteDesc() {
        return executeDesc;
    }

    public void setExecuteDesc(String executeDesc) {
        this.executeDesc = executeDesc;
    }


    public String getQryDesc() {
        return qryDesc;
    }

    public void setQryDesc(String qryDesc) {
        this.qryDesc = qryDesc;
    }


    public Integer getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(Integer failTimes) {
        this.failTimes = failTimes;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getWithDrawalsFlag() {
        return withDrawalsFlag;
    }

    public void setWithDrawalsFlag(Integer withDrawalsFlag) {
        this.withDrawalsFlag = withDrawalsFlag;
    }


    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }


    public Integer getQryTimes() {
        return qryTimes;
    }

    public void setQryTimes(Integer qryTimes) {
        this.qryTimes = qryTimes;
    }


    public Integer getLastQryStatus() {
        return lastQryStatus;
    }

    public void setLastQryStatus(Integer lastQryStatus) {
        this.lastQryStatus = lastQryStatus;
    }


    public String getLastOrderId() {
        return lastOrderId;
    }

    public void setLastOrderId(String lastOrderId) {
        this.lastOrderId = lastOrderId;
    }


    public String getLastWithDrawalsNo() {
        return lastWithDrawalsNo;
    }

    public void setLastWithDrawalsNo(String lastWithDrawalsNo) {
        this.lastWithDrawalsNo = lastWithDrawalsNo;
    }


    public Integer getFailType() {
        return failType;
    }

    public void setFailType(Integer failType) {
        this.failType = failType;
    }


    public Integer getQryAgain() {
        return qryAgain;
    }

    public void setQryAgain(Integer qryAgain) {
        this.qryAgain = qryAgain;
    }

    public String getRealExecuteTime() {
        return realExecuteTime;
    }

    public void setRealExecuteTime(String realExecuteTime) {
        this.realExecuteTime = realExecuteTime;
    }

    public BigDecimal getRealCostRate() {
        return realCostRate;
    }

    public void setRealCostRate(BigDecimal realCostRate) {
        this.realCostRate = realCostRate;
    }

    public BigDecimal getRealCostFee() {
        return realCostFee;
    }

    public void setRealCostFee(BigDecimal realCostFee) {
        this.realCostFee = realCostFee;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}

