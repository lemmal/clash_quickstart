package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.logger.ClashLogger;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.KBContext;
import com.clashquickstart.killboss.KBUtil;

@BeanConsumer
public class KBInvokeProcessor implements IProcessor {
    @BeanAutowire
    private IContext context;

    @Override
    public IResult process(IParam param) {
        ClashLogger.info("process : invoke");
        KBContext kbContext = KBUtil.toKBContext(this.context);
        kbContext.decrBossHp(100);
        return () -> true;
    }
}
