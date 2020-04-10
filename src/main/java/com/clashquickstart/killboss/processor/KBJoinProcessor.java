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
import com.clashquickstart.killboss.param.KBJoinParam;

@BeanConstruct(value = IProcessor.class, name = "Join")
@BeanConsumer
public class KBJoinProcessor implements IProcessor {
    @BeanAutowire
    private IContext context;

    @SuppressWarnings("unchecked")
    @Override
    public IResult process(IParam param) {
        KBJoinParam p = param.toObject(KBJoinParam.class);
        ClashLogger.info("process : join");
        IPlayerContainer<Long> container = (IPlayerContainer<Long>) context.getPlayerContainer();
        return container.join(p.getUserId());
    }
}
