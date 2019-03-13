package com.gp.pattern.prototype.jobMain;


import com.gp.pattern.prototype.oldpo.JxfPlanItem;
import com.gp.pattern.prototype.oldpo.PlanDetailResp;

import java.util.ArrayList;
import java.util.List;

public class ProtoTypeOld {


    public static void main(String[] args) {
        List<PlanDetailResp> planDetailResps = new ArrayList();
        List<JxfPlanItem> jxfPlanItemList = new ArrayList();
        JxfPlanItem jxfPlanItem1 = new JxfPlanItem(1);
        JxfPlanItem jxfPlanItem2 = new JxfPlanItem(2);
        JxfPlanItem jxfPlanItem3 = new JxfPlanItem(3);
        jxfPlanItemList.add(jxfPlanItem1);
        jxfPlanItemList.add(jxfPlanItem2);
        jxfPlanItemList.add(jxfPlanItem3);

        System.out.println("before-----------------");
        for (int i = 0; i < jxfPlanItemList.size(); ++i) {
            System.out.println("PlanMoney:" + jxfPlanItemList.get(i).getPlanMoney());
        }
        for (int i = 0; i < jxfPlanItemList.size(); ++i) {
            JxfPlanItem item = jxfPlanItemList.get(i);
            PlanDetailResp planDetailResp = new PlanDetailResp();
            planDetailResp.setPlanTime(item.getPlanTime().split(" ")[1]);
            planDetailResp.setPlanDay(item.getPlanTime().split(" ")[0]);
            planDetailResp.setItemType(item.getItemType());
            planDetailResp.setItemName("消费");
            planDetailResp.setCategoryName(item.getCategoryName());
            planDetailResp.setCostMoney(item.getCostMoney());
            planDetailResp.setPlanMoney(item.getPlanMoney());
            planDetailResp.setStatus(item.getStatus());
            planDetailResp.setFailType(item.getFailType());
            planDetailResp.setPlanIndex(item.getPlanIndex());
            planDetailResps.add(planDetailResp);
        }
        System.out.println("after------------------");
        for (int i = 0; i < planDetailResps.size(); ++i) {
            System.out.println("PlanMoney:" + planDetailResps.get(i).getPlanMoney());
        }
    }
}
