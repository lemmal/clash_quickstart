package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IPlayerContainer;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.logger.ClashLogger;
import com.clash.processor.IProcessor;

@BeanConsumer
public class LeaveProcessor implements IProcessor {
    @BeanAutowire
    private IContext context;

    @SuppressWarnings("unchecked")
    @Override
    public IResult process() {
        ClashLogger.info("process : leave");
        IPlayerContainer<Long> container = (IPlayerContainer<Long>) context.getPlayerContainer();
        container.leave(0L);
        return null;
    }
}
