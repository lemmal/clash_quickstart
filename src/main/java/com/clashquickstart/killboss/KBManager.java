package com.clashquickstart.killboss;

import com.clash.IContext;
import com.clash.IManager;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConstruct;
import com.clash.bean.BeanConsumer;
import com.clash.param.IParam;

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
    public IResult join(IParam param) {
        return context.getSynchronizer().submit(() -> context.getJoinPipeline().process(param));
    }

    @Override
    public IResult leave(IParam param) {
        return context.getSynchronizer().submit(() -> context.getLeavePipeline().process(param));
    }

    @Override
    public IResult invoke(IParam param) {
        return context.getSynchronizer().submit(() -> context.getInvokePipeline().process(param));
    }
}
