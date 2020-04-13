package com.clashquickstart.killboss;

import com.clash.IContext;
import com.clash.bean.BeanAutowire;
import com.clash.bean.BeanConsumer;
import com.clash.bean.IBeanProvider;
import com.clash.component.ComponentContainer;
import com.clash.component.state.StateComponent;
import com.clash.component.state.StateRegister;
import com.clash.processor.IProcessor;
import com.clash.processor.ProcessorPipeline;
import com.clash.synchronizer.CASSynchronizer;
import com.clash.synchronizer.ISynchronizer;
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

    @BeanConsumer
    public static class JoinPipelineProvider implements IBeanProvider<ProcessorPipeline> {
        @BeanAutowire("Join")
        private IProcessor join;
        @BeanAutowire("Start")
        private IProcessor start;
        @BeanAutowire("PlayerNum")
        private IProcessor playerNum;

        @Override
        public ProcessorPipeline provide() {
            return new ProcessorPipeline().addLast(join).addLast(start).addLast(playerNum);
        }

        @Override
        public String name() {
            return "Join";
        }
    }

    @BeanConsumer
    public static class LeavePipelineProvider implements IBeanProvider<ProcessorPipeline> {
        @BeanAutowire("Leave")
        private IProcessor leave;
        @BeanAutowire("PlayerNum")
        private IProcessor playerNum;

        @Override
        public ProcessorPipeline provide() {
            return new ProcessorPipeline().addLast(leave).addLast(playerNum);
        }

        @Override
        public String name() {
            return "Leave";
        }
    }

    @BeanConsumer
    public static class InvokePipelineProvider implements IBeanProvider<ProcessorPipeline> {
        @BeanAutowire("Invoke")
        private IProcessor invoke;
        @BeanAutowire("End")
        private IProcessor end;

        @Override
        public ProcessorPipeline provide() {
            return new ProcessorPipeline().addLast(invoke).addLast(end);
        }

        @Override
        public String name() {
            return "Invoke";
        }
    }

    @BeanConsumer
    public static class ComponentContianerProvider implements IBeanProvider<ComponentContainer> {
        @BeanAutowire
        private IContext context;

        @Override
        public ComponentContainer provide() {
            ComponentContainer container = new ComponentContainer();
            container.register(new StateComponent(context, StateRegister.createFromEnum(KBState.class, KBState.WAIT)));
            return container;
        }
    }

}
