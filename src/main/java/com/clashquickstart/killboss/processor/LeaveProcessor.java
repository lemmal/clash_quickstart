package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IPlayerContainer;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.logger.ClashLogger;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.param.LeaveParam;

@BeanConsumer
public class LeaveProcessor implements IProcessor {
    @BeanAutowire
    private IContext context;

    @SuppressWarnings("unchecked")
    @Override
    public IResult process(IParam param) {
        LeaveParam p = param.toObject(LeaveParam.class);
        ClashLogger.info("process : leave");
        IPlayerContainer<Long> container = (IPlayerContainer<Long>) context.getPlayerContainer();
        container.leave(p.getUserId());
        return null;
    }
}
