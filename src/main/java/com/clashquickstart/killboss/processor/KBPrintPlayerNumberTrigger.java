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

@BeanConstruct(value = IProcessor.class, name = "PlayerNum")
@BeanConsumer
public class KBPrintPlayerNumberTrigger implements IProcessor {
    @BeanAutowire
    IContext context;

    @Override
    public IResult process(IParam param) {
        IPlayerContainer<?> container = context.getPlayerContainer();
        ClashLogger.info("player num : {}", container.getPlayerNumber());
        return () -> true;
    }
}