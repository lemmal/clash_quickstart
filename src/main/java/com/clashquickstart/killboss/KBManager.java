package com.clashquickstart.killboss;

import com.clash.IContext;
import com.clash.IManager;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConstruct;
import com.clash.bean.BeanConsumer;

@BeanConsumer
@BeanConstruct(IManager.class)
public class KBManager implements IManager {
    @BeanAutowire
    IContext context;

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public IResult join() {
        return context.getSynchronizer().submit(() -> context.getJoinPipeline().process());
    }

    @Override
    public IResult leave() {
        return context.getSynchronizer().submit(() -> context.getLeavePipeline().process());
    }

    @Override
    public IResult invoke() {
        return context.getSynchronizer().submit(() -> context.getInvokePipeline().process());
    }
}
