package com.clashquickstart.killboss;

import com.clash.IManager;
import com.clash.bean.BeanConstructException;
import com.clash.bean.BeanFactory;
import com.clash.bean.BeanParseException;

public enum KBBeanFactory {
    INSTANCE;

    private BeanFactory bf;

    public void init() throws BeanParseException, BeanConstructException {
        if(null == bf) {
            bf = BeanFactory.init("com.clashquickstart.killboss");
        }
    }

    public void initInstance() throws BeanConstructException {
        bf.initInstances();
    }

    public <T extends IManager> T getManager(Class<T> clazz) throws BeanConstructException {
        return bf.getManager(clazz);
    }

    public <T> T getBean(Class<T> clazz, String name) throws BeanConstructException {
        return bf.consumeBean(clazz, name);
    }
}
