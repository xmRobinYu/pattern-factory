/*
* @(#)PlanWithoutLoginVoReq.java
*
* Copyright 2017-2018, Inc. All rights reserved.
*
*/
package com.gp.pattern.prototype.newPo;

import java.io.*;
import java.math.BigDecimal;

/**
 * Class <code>PlanWithoutLoginVoReq</code> is the root of the class hierarchy.
 * Every class has <code>PlanWithoutLoginVoReq</code> as a superclass. All objects,
 * including arrays, implement the methods of this class.
 *
 * @author yuxb
 * @version 2018-06-05
 * @see Class
 */

public class PlanDetailResp implements Cloneable, Serializable {

    //计划类型
    protected Integer itemType;
    //计划时间
    protected String planTime;
    //计划日期
    protected String planDay;
    //计划金额
    protected BigDecimal planMoney;
    //手续费
    protected BigDecimal costMoney;
    //行业类目名称
    protected String categoryName;
    //状态
    protected Integer status;
    //失败类型
    protected Integer failType;

    protected Integer planIndex;

    protected String itemName;

    public PlanDetailResp() {
    }

    public PlanDetailResp(Integer itemType, String planTime, String planDay, BigDecimal planMoney, BigDecimal costMoney, String categoryName, Integer status, Integer failType, Integer planIndex, String itemName) {
        this.itemType = itemType;
        this.planTime = planTime;
        this.planDay = planDay;
        this.planMoney = planMoney;
        this.costMoney = costMoney;
        this.categoryName = categoryName;
        this.status = status;
        this.failType = failType;
        this.planIndex = planIndex;
        this.itemName = itemName;
    }

    public PlanDetailResp(Integer planIndex) {
        this.planIndex = planIndex;
        this.itemType = 1;
        this.planTime = "2019-03-12 15:25:12";
        this.planMoney = new BigDecimal(100).add(new BigDecimal(planIndex));
        this.costMoney = new BigDecimal(12);
        this.categoryName = "12121adsfasf";
        this.status = 1;
        this.failType = 1;
    }

    //深克隆
    public PlanDetailResp deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            PlanDetailResp copy = (PlanDetailResp) ois.readObject();
            ois.close();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //浅克隆
    public void shallowClone(PlanDetailResp target) {
        this.planMoney = target.planMoney;
        //...
    }

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

