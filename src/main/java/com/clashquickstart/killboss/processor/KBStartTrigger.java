package com.clashquickstart.killboss.processor;

import com.clash.IContext;
import com.clash.IResult;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.component.state.IStateComponent;
import com.clash.component.state.StateComponent;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;
import com.clashquickstart.killboss.KBConstants;
import com.clashquickstart.killboss.state.KBState;

@BeanConsumer
public class KBStartTrigger implements IProcessor {
    @BeanAutowire
    IContext context;

    @Override
    public IResult process(IParam param) {
        IStateComponent component = context.getComponentContainer().getComponent(StateComponent.class);
        int stateId = component.getInfo().getStateId();
        if(context.getPlayerContainer().getPlayerNumber() >= KBConstants.MAX_PLAYER_NUM && stateId == KBState.WAIT.getStateId()) {
            component.changeToNext();
        }
        return null;
    }
}
