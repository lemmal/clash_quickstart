package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IPlayerContainer;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConstruct;
import com.clash.bean.BeanConsumer;
import com.clash.logger.ClashLogger;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.param.KBLeaveParam;

@BeanConstruct(value = IProcessor.class, name = "Leave")
@BeanConsumer
public class KBLeaveProcessor implements IProcessor {
    @BeanAutowire
    private IContext context;

    @SuppressWarnings("unchecked")
    @Override
    public IResult process(IParam param) {
        KBLeaveParam p = param.toObject(KBLeaveParam.class);
        ClashLogger.info("process : leave");
        IPlayerContainer<Long> container = (IPlayerContainer<Long>) context.getPlayerContainer();
        return container.leave(p.getUserId());
    }
}
