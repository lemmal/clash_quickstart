package com.clashquickstart.killboss.state;

import com.clash.IContext;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.component.state.IState;
import com.clash.logger.ClashLogger;

@BeanConsumer
public enum KBState implements IState {
    WAIT {
        @Override
        public int getNextStateId() {
            return START.getStateId();
        }

        @Override
        public long getStateSecond() {
            return LAST_FOREVER;
        }

        @Override
        public void onStateStart() {
            ClashLogger.info("KBState : WAIT");
        }
    },
    START {
        @Override
        public int getNextStateId() {
            return END.getStateId();
        }

        @Override
        public long getStateSecond() {
            return LAST_FOREVER;
        }

        @Override
        public void onStateStart() {
            ClashLogger.info("KBState : START");
        }
    },
    END {
        @Override
        public int getNextStateId() {
            return NULL_STATE;
        }

        @Override
        public long getStateSecond() {
            return 5;
        }

        @Override
        public void onStateStart() {
            ClashLogger.info("KBState : END");
        }
    }
    ;

    @BeanAutowire
    private IContext context;


    @Override
    public int getStateId() {
        return ordinal();
    }
}
