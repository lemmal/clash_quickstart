package com.clashquickstart;


import com.clash.bean.BeanConstructException;
import com.clash.bean.BeanParseException;
import com.clash.param.HashBasedParam;
import com.clashquickstart.killboss.KBBeanFactory;
import com.clashquickstart.killboss.KBCommands;
import com.clashquickstart.killboss.KBManager;
import com.clashquickstart.killboss.param.KBHitParam;
import com.clashquickstart.killboss.param.KBJoinParam;
import com.clashquickstart.killboss.param.KBLeaveParam;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 测试引导
 */
public class Bootstrap {

    private static ExecutorService[] EXECUTORS = init();
    private static CountDownLatch LATCH = new CountDownLatch(1);

    private static ExecutorService[] init() {
        AtomicInteger index = new AtomicInteger(0);
        return Stream.generate(() -> Executors.newSingleThreadExecutor(buildFactory(index.getAndIncrement()))).limit(2).toArray(ExecutorService[]::new);
    }

    private static ThreadFactory buildFactory(int index) {
        return new ThreadFactoryBuilder().setNameFormat(String.format("KB_Worker-%s", index)).setDaemon(true).build();
    }

    public static void main(String[] args) throws BeanParseException, BeanConstructException, InterruptedException {
        KBBeanFactory.INSTANCE.init();
        KBManager manager = KBBeanFactory.INSTANCE.getManager(KBManager.class);
        manager.init();
        manager.start();
        CompletableFuture<Void> fc1 = CompletableFuture.runAsync(() -> manager.join(HashBasedParam.create(new KBJoinParam(1))), getExecutor(1));
        fc1.thenRunAsync(() -> manager.invoke(HashBasedParam.createWithCommand(new KBHitParam(1, 100), KBCommands.HIT)), getExecutor(1));
        fc1.thenRunAsync(() -> manager.leave(HashBasedParam.create(new KBLeaveParam(1))), getExecutor(1));
        CompletableFuture<Void> fc2 = CompletableFuture.runAsync(() -> manager.join(HashBasedParam.create(new KBJoinParam(2))), getExecutor(2));
        CompletableFuture.allOf(fc1, fc2).thenRun(() -> {
            manager.destroy();
            LATCH.countDown();
        });
        LATCH.await();
    }

    private static ExecutorService getExecutor(long userId) {
        long index = userId % EXECUTORS.length;
        return EXECUTORS[(int) index];
    }

}
