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
            bf = BeanFactory.scan("com.clashquickstart.killboss");
            bf.init();
        }
    }

    public <T extends IManager> T getManager(Class<T> clazz) throws BeanConstructException {
        return bf.getManager(clazz);
    }

    public BeanFactory getBeanFactory() {
        return bf;
    }
}
