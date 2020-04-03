package com.clashquickstart.killboss.processor;

import com.clash.IResult;
import com.clash.logger.ClashLogger;
import com.clash.processor.IProcessor;

public class InvokeProcessor implements IProcessor {

    @Override
    public IResult process() {
        ClashLogger.info("process : invoke");
        return null;
    }
}
