package com.gp.pattern.singleton.hungry;

/**
 *    
 *   * 
 *   * Simple To Introduction 
 *   * 类描述:    [一句话描述该类的功能]
 *   * 创建人:    yuxb   
 *   * 创建时间:  [${date} ${time}]   
 *   * 修改人:    [${user}]   
 *   * 修改时间:  [${date} ${time}]   
 *   * 修改备注:  [说明本次修改内容]  
 *   * 版本:      [v1.0]   
 *   *    
 *  
 */
public class HungrySingletionThread implements Runnable {

    @Override
    public void run() {
        HungrySingletion hungrySingletion = HungrySingletion.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + hungrySingletion);

        HungryStaticSingletion hungrySingletion2 = HungryStaticSingletion.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + hungrySingletion2);
    }
}
