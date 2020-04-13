package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConstruct;
import com.clash.bean.BeanConsumer;
import com.clash.bean.InvokeHandlerParser;
import com.clash.component.state.IStateComponent;
import com.clash.handler.IInvokeHandler;
import com.clash.handler.InvokeCommand;
import com.clash.logger.ClashLogger;
import com.clash.param.ICommandParam;
import com.clash.processor.CommandProcessor;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.KBBeanFactory;
import com.clashquickstart.killboss.KBCommands;
import com.clashquickstart.killboss.KBContext;
import com.clashquickstart.killboss.KBUtil;
import com.clashquickstart.killboss.param.KBHitParam;
import com.clashquickstart.killboss.state.KBState;

import java.util.Map;

@BeanConstruct(value = IProcessor.class, name = "Invoke")
public class KBInvokeProcessor extends CommandProcessor {
    private Map<String, IInvokeHandler> handlers = InvokeHandlerParser.parseHandlers(KBInvokeProcessor.class, KBBeanFactory.INSTANCE.getBeanFactory());

    @Override
    protected IResult _process(ICommandParam param) {
        ClashLogger.info("process : invoke");
        IInvokeHandler handler = handlers.get(param.getCommand());
        return handler.invoke(param);
    }

    @BeanConsumer
    @InvokeCommand(KBCommands.HIT)
    public static class HitHandler implements IInvokeHandler {
        @BeanAutowire
        private IContext context;

        @Override
        public IResult invoke(ICommandParam param) {
            IStateComponent component = context.getComponentContainer().getComponent(IStateComponent.class);
            if(component.getInfo().getStateId() != KBState.START.getStateId()) {
                ClashLogger.info("invalid state : {}", KBState.values()[component.getInfo().getStateId()]);
                return () -> false;
            }
            KBHitParam hitParam = param.toObject(KBHitParam.class);
            KBContext kbContext = KBUtil.toKBContext(this.context);
            kbContext.decrBossHp(hitParam.getHp());
            ClashLogger.info("boss hp : {}", kbContext.getBossHp());
            return () -> true;
        }
    }
}
