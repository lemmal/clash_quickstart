package com.clashquickstart;


import com.clash.IContext;
import com.clash.bean.BeanConstructException;
import com.clash.bean.BeanParseException;
import com.clash.logger.ClashLogger;
import com.clashquickstart.killboss.KBBeanFactory;
import com.clashquickstart.killboss.KBManager;

/**
 * 测试引导
 */
public class Bootstrap {

    public static void main(String[] args) throws BeanParseException, BeanConstructException {
        KBBeanFactory.INSTANCE.init();
        KBBeanFactory.INSTANCE.initInstance();
        KBManager manager = KBBeanFactory.INSTANCE.getManager(KBManager.class);
        manager.init();
        manager.start();
        manager.join();
        IContext context = KBBeanFactory.INSTANCE.getBean(IContext.class, "");
        ClashLogger.info(String.valueOf(context.getPlayerContainer().getPlayerNumber()));
        manager.invoke();
        manager.leave();
        ClashLogger.info(String.valueOf(context.getPlayerContainer().getPlayerNumber()));;
        manager.destroy();
    }

}
