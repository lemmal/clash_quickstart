package com.clashquickstart.killboss;

import com.clash.IContext;
import com.clash.IPlayerContainer;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConstruct;
import com.clash.bean.BeanConsumer;
import com.clash.component.ComponentContainer;
import com.clash.processor.ProcessorPipeline;
import com.clash.synchronizer.ISynchronizer;

import java.util.concurrent.ScheduledExecutorService;

@BeanConsumer
@BeanConstruct(IContext.class)
public class KBContext implements IContext {
    @BeanAutowire
    private ScheduledExecutorService scheduler;
    @BeanAutowire
    private ISynchronizer synchronizer;
    @BeanAutowire
    private IPlayerContainer<?> playerContainer;
    @BeanAutowire
    private ComponentContainer componentContainer;
    @BeanAutowire("Join")
    private ProcessorPipeline joinPipeline;
    @BeanAutowire("Leave")
    private ProcessorPipeline leaveProcessor;
    @BeanAutowire("Invoke")
    private ProcessorPipeline invokeProcessor;

    @Override
    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    @Override
    public ISynchronizer getSynchronizer() {
        return synchronizer;
    }

    @Override
    public IPlayerContainer<?> getPlayerContainer() {
        return playerContainer;
    }

    @Override
    public ComponentContainer getComponentContainer() {
        return componentContainer;
    }

    @Override
    public ProcessorPipeline getJoinPipeline() {
        return joinPipeline;
    }

    @Override
    public ProcessorPipeline getLeavePipeline() {
        return leaveProcessor;
    }

    @Override
    public ProcessorPipeline getInvokePipeline() {
        return invokeProcessor;
    }
}
