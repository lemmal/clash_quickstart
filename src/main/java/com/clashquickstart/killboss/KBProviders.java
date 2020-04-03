package com.clashquickstart.killboss;

import com.clash.bean.BeanFactory;
import com.clash.bean.IBeanProvider;
import com.clash.component.ComponentContainer;
import com.clash.component.state.StateComponent;
import com.clash.processor.ProcessorPipeline;
import com.clash.synchronizer.CASSynchronizer;
import com.clash.synchronizer.ISynchronizer;
import com.clashquickstart.killboss.processor.InvokeProcessor;
import com.clashquickstart.killboss.processor.JoinProcessor;
import com.clashquickstart.killboss.processor.LeaveProcessor;

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
            return new ProcessorPipeline().addLast(new JoinProcessor());
        }

        @Override
        public String name() {
            return "Join";
        }
    }

    public static class LeavePipelineProvider implements IBeanProvider<ProcessorPipeline> {

        @Override
        public ProcessorPipeline provide() {
            return new ProcessorPipeline().addLast(new LeaveProcessor());
        }

        @Override
        public String name() {
            return "Leave";
        }
    }

    public static class InvokePipelineProvider implements IBeanProvider<ProcessorPipeline> {

        @Override
        public ProcessorPipeline provide() {
            return new ProcessorPipeline().addLast(new InvokeProcessor());
        }

        @Override
        public String name() {
            return "Invoke";
        }
    }

    public static class ComponentContianerProvider implements IBeanProvider<ComponentContainer> {

        @Override
        public ComponentContainer provide() {
            ComponentContainer container = new ComponentContainer();

            container.register(new StateComponent());
        }
    }
}
