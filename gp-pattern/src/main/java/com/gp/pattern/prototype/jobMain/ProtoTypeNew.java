package com.gp.pattern.prototype.jobMain;


import com.gp.pattern.prototype.newPo.PlanDetailResp;

import java.util.ArrayList;
import java.util.List;

public class ProtoTypeNew {


    public static void main(String[] args) {
        List<PlanDetailResp> planDetailResps = new ArrayList();
        List<PlanDetailResp> jxfPlanItemList = new ArrayList();
        PlanDetailResp jxfPlanItem1 = new PlanDetailResp(1);
        PlanDetailResp jxfPlanItem2 = new PlanDetailResp(2);
        PlanDetailResp jxfPlanItem3 = new PlanDetailResp(3);
        jxfPlanItemList.add(jxfPlanItem1);
        jxfPlanItemList.add(jxfPlanItem2);
        jxfPlanItemList.add(jxfPlanItem3);

        System.out.println("before-----------------");
        for (int i = 0; i < jxfPlanItemList.size(); ++i) {
            System.out.println("PlanMoney:" + jxfPlanItemList.get(i).getPlanMoney());
        }
        for (int i = 0; i < jxfPlanItemList.size(); ++i) {
            PlanDetailResp planDetailResp = new PlanDetailResp();
            planDetailResp.shallowClone(jxfPlanItemList.get(i));
            planDetailResps.add(planDetailResp);
        }
        System.out.println("浅克隆后 after------------------");
        for (int i = 0; i < planDetailResps.size(); ++i) {
            System.out.println("PlanMoney:" + planDetailResps.get(i).getPlanMoney());
        }

        List<PlanDetailResp> planDetailResps2 = new ArrayList();
        for (int i = 0; i < jxfPlanItemList.size(); ++i) {
            PlanDetailResp planDetailResp = new PlanDetailResp();
            planDetailResp = jxfPlanItemList.get(i).deepClone();
            planDetailResps2.add(planDetailResp);
        }
        System.out.println("深克隆后 after------------------");
        for (int i = 0; i < planDetailResps2.size(); ++i) {
            System.out.println("PlanMoney:" + planDetailResps2.get(i).getPlanMoney());
        }
    }
}
