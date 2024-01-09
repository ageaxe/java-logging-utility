package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;
import org.slf4j.ILoggerFactory;
public class DebugSLF4JServiceProvider implements SLF4JServiceProvider{

    private ILoggerFactory loggerFactory;

    @Override
    public ILoggerFactory getLoggerFactory() {
        if (loggerFactory == null) {
            loggerFactory = new DebugLoggerFactory();
        }
        return loggerFactory;
    }

    @Override
    public void initialize() {
        loggerFactory = new DebugLoggerFactory();
    }


    @Override
    public IMarkerFactory getMarkerFactory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMarkerFactory'");
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMDCAdapter'");
    }

    @Override
    public String getRequestedApiVersion() {
       return "1.0.0";  // replace with the actual version of your implementation
    }
}
