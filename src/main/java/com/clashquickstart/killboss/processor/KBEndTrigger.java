package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.component.state.IStateComponent;
import com.clash.component.state.StateComponent;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.KBContext;
import com.clashquickstart.killboss.KBUtil;
import com.clashquickstart.killboss.state.KBState;

@BeanConsumer
public class KBEndTrigger implements IProcessor {
    @BeanAutowire
    IContext context;

    @Override
    public IResult process(IParam param) {
        IStateComponent component = context.getComponentContainer().getComponent(StateComponent.class);
        int stateId = component.getInfo().getStateId();
        KBContext kbContext = KBUtil.toKBContext(context);
        if(stateId == KBState.START.getStateId() && kbContext.getBossHp() <= 0) {
            component.changeToNext();
        }
        return null;
    }
}
