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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 测试引导
 */
public class Bootstrap {

    private static ExecutorService[] EXECUTORS = init();

    private static ExecutorService[] init() {
        AtomicInteger index = new AtomicInteger(0);
        return Stream.generate(() -> Executors.newSingleThreadExecutor(buildFactory(index.getAndIncrement()))).limit(2).toArray(ExecutorService[]::new);
    }

    private static ThreadFactory buildFactory(int index) {
        return new ThreadFactoryBuilder().setNameFormat(String.format("KB_Worker-%s", index)).setDaemon(true).build();
    }

    public static void main(String[] args) throws BeanParseException, BeanConstructException, InterruptedException {
        KBBeanFactory.INSTANCE.init();
        KBBeanFactory.INSTANCE.initInstance();
        KBManager manager = KBBeanFactory.INSTANCE.getManager(KBManager.class);
        manager.init();
        manager.start();
        submit(1, () -> manager.join(HashBasedParam.create(new KBJoinParam(1))));
        submit(2, () -> manager.join(HashBasedParam.create(new KBJoinParam(2))));
        submit(1, () -> manager.invoke(HashBasedParam.createWithCommand(new KBHitParam(1, 100), KBCommands.HIT)));
        submit(1, () -> manager.leave(HashBasedParam.create(new KBLeaveParam(1))));
        Thread.sleep(2000);
        manager.destroy();
    }

    private static void submit(long userId, Runnable runnable) {
        long index = userId % EXECUTORS.length;
        EXECUTORS[(int) index].submit(runnable);

    }

}
