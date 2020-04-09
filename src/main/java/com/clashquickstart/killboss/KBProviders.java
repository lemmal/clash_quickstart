package com.clashquickstart.killboss;

import com.clash.bean.BeanConstructException;
import com.clash.bean.IBeanProvider;
import com.clash.component.ComponentContainer;
import com.clash.component.state.StateComponent;
import com.clash.component.state.StateRegister;
import com.clash.processor.ProcessorPipeline;
import com.clash.synchronizer.CASSynchronizer;
import com.clash.synchronizer.ISynchronizer;
import com.clashquickstart.killboss.processor.*;
import com.clashquickstart.killboss.state.KBState;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class KBProviders {

    public static class SchedulerProvider implements IBeanProvider<ScheduledExecutorService> {

        @Override
        public ScheduledExecutorService provide() {
            return Executors.newSingleThreadScheduledExecutor();
        }
    }

    public static class SynchronizerProvider implements IBeanProvider<ISynchronizer> {

        @Override
        public ISynchronizer provide() {
            return new CASSynchronizer();
        }
    }

    public static class JoinPipelineProvider implements IBeanProvider<ProcessorPipeline> {

        @Override
        public ProcessorPipeline provide() {
            try {
                return new ProcessorPipeline()
                        .addLast(KBBeanFactory.INSTANCE.getBean(KBJoinProcessor.class, ""))
                        .addLast(KBBeanFactory.INSTANCE.getBean(KBStartTrigger.class, ""));
            } catch (BeanConstructException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String name() {
            return "Join";
        }
    }

    public static class LeavePipelineProvider implements IBeanProvider<ProcessorPipeline> {

        @Override
        public ProcessorPipeline provide() {
            try {
                return new ProcessorPipeline().addLast(KBBeanFactory.INSTANCE.getBean(KBLeaveProcessor.class, ""));
            } catch (BeanConstructException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String name() {
            return "Leave";
        }
    }

    public static class InvokePipelineProvider implements IBeanProvider<ProcessorPipeline> {

        @Override
        public ProcessorPipeline provide() {
            try {
                return new ProcessorPipeline()
                        .addLast(KBBeanFactory.INSTANCE.getBean(KBInvokeProcessor.class, ""))
                        .addLast(KBBeanFactory.INSTANCE.getBean(KBEndTrigger.class, ""));
            } catch (BeanConstructException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String name() {
            return "Invoke";
        }
    }

    public static class ComponentContianerProvider implements IBeanProvider<ComponentContainer> {

        @Override
        public ComponentContainer provide() {
            try {
                ComponentContainer container = new ComponentContainer();
                container.register(KBBeanFactory.INSTANCE.getBean(StateComponent.class, ""));
                return container;
            } catch (BeanConstructException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class StateRegisterProvider implements IBeanProvider<StateRegister> {

        @Override
        public StateRegister provide() {
            return StateRegister.createFromEnum(KBState.class, KBState.WAIT);
        }
    }
}
