package com.gp.pattern.singleton.threadlocal;


import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadLocalSingletonTest {

    private static final int CONCURRENT_THREAD_NUMBER = 200;

    @Test
    public void concurrentGetSingletonWithCyclicBarrierTest() throws Exception {
        final CyclicBarrier barrier = new CyclicBarrier(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch latch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<String>();

        new Thread(() -> {
            try {
//                barrier.await();
                for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
                    ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
                    System.out.println("当前时间: {}, 单例: {}" + System.currentTimeMillis() + "   " + instance.toString());
                    instanceUrl.add(instance.toString());
//                    latch.countDown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

//    latch.await();

    //System.out.println("模拟并发线程数：{"+CONCURRENT_THREAD_NUMBER+"}, 实际并发线程数：{"+instanceUrl.size()+"}");
   // Assert.assertEquals("线程数："+CONCURRENT_THREAD_NUMBER,"线程数："+"   "+instanceUrl.size());

    // 验证并发获取到的单例是否是同一个实例
    this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);

}

    @Test
    public void concurrentGetSingletonWithCountDownLatchTest() throws Exception {
        final CountDownLatch workThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);
        final CountDownLatch mainThreadLatch = new CountDownLatch(CONCURRENT_THREAD_NUMBER);

        // 统计并发获取到的单例对象引用地址，用于验证获取到的单例是否是同一个
        final List<String> instanceUrl = new Vector<String>();

        for (int i = 0; i < CONCURRENT_THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    workThreadLatch.await();

                    ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();

                    System.out.println("当前时间: {}, 单例: {}" + System.currentTimeMillis() + "   " + instance.toString());
                    instanceUrl.add(instance.toString());

                    mainThreadLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("线程 {} 异常: {}" + Thread.currentThread().getName() + "   " + e.getMessage());
                }
            }).start();

            workThreadLatch.countDown();
        }

        mainThreadLatch.await();

        System.out.println("模拟并发线程数：{" + CONCURRENT_THREAD_NUMBER + "}, 实际并发线程数：{" + instanceUrl.size() + "}");
        Assert.assertEquals("线程数：" + CONCURRENT_THREAD_NUMBER, "线程数：" + instanceUrl.size());

        // 验证并发获取到的单例是否是同一个实例
        this.verifySingletonsIsSameOrNotWithAssert(instanceUrl);
    }

    @Test
    public void breakSingletonByReflectionTest() throws Exception {
        ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();

        Constructor<ThreadLocalSingleton> constructor = ThreadLocalSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        ThreadLocalSingleton singletonFromReflection = constructor.newInstance();

        System.out.println("正常单例：{}" + singleton.toString());
        System.out.println("反射单例：{}" + singletonFromReflection.toString());
        Assert.assertEquals(singleton.toString(), singletonFromReflection.toString());
    }

    /**
     * 验证并发获取到的单例是否是同一个实例
     */
    private void verifySingletonsIsSameOrNotWithAssert(final List<String> instanceUrl) {
        for (int i = 0; i < instanceUrl.size(); i++) {
            if (i == instanceUrl.size() - 1) {
                Assert.assertEquals(instanceUrl.get(i - 1), instanceUrl.get(i));
                break;
            }

            Assert.assertEquals(instanceUrl.get(i), instanceUrl.get(i + 1));
        }
    }

}
