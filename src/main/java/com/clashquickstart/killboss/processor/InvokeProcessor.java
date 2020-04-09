package com.clashquickstart.killboss.processor;

import com.clash.IResult;
import com.clash.logger.ClashLogger;
import com.clash.param.IParam;
import com.clash.processor.IProcessor;

public class InvokeProcessor implements IProcessor {

    @Override
    public IResult process(IParam param) {
        ClashLogger.info("process : invoke");
        return null;
    }
}
