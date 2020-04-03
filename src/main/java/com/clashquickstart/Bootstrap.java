package com.clashquickstart;


import com.clash.bean.BeanConstructException;
import com.clash.bean.BeanFactory;
import com.clash.bean.BeanParseException;
import com.clashquickstart.killboss.KBManager;

/**
 * 测试引导
 */
public class Bootstrap {

    public static void main(String[] args) throws BeanParseException, BeanConstructException {
        BeanFactory
        KBManager manager = BeanFactory.buildManager(KBManager.class, "com.clashquickstart.killboss");
        manager.init();
        manager.start();
        manager.join();
        manager.invoke();
        manager.leave();
        manager.destroy();
    }

}
