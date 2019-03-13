/*
* @(#)PlanWithoutLoginVoReq.java
*
* Copyright 2017-2018, Inc. All rights reserved.
*
*/
package com.gp.pattern.prototype.oldpo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* Class <code>PlanWithoutLoginVoReq</code> is the root of the class hierarchy.
* Every class has <code>PlanWithoutLoginVoReq</code> as a superclass. All objects,
* including arrays, implement the methods of this class.
*
* @author   yuxb
* @version  2018-06-05
* @see      Class
*/

public class PlanDetailResp implements Serializable {

    //计划类型
    private Integer itemType;
    //计划时间
    private String planTime;
    //计划日期
    private String planDay;
    //计划金额
    private BigDecimal planMoney;
    //手续费
    private BigDecimal costMoney;
    //行业类目名称
    private String categoryName;
    //状态
    private Integer status;
   //失败类型
    private Integer failType;

    private Integer planIndex;

    private String itemName;

    public String getPlanDay() {
        return planDay;
    }

    public void setPlanDay(String planDay) {
        this.planDay = planDay;
    }

    public Integer getPlanIndex() {
        return planIndex;
    }

    public void setPlanIndex(Integer planIndex) {
        this.planIndex = planIndex;
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

    public BigDecimal getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(BigDecimal planMoney) {
        this.planMoney = planMoney;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(BigDecimal costMoney) {
        this.costMoney = costMoney;
    }

    public Integer getFailType() {
        return failType;
    }

    public void setFailType(Integer failType) {
        this.failType = failType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}

