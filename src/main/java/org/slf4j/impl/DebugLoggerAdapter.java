package org.slf4j.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.Marker;


public class DebugLoggerAdapter implements Logger {

    private final ch.qos.logback.classic.Logger logger;

    private final Queue<String> debugMessages = new LinkedList<>();

    private final int maxDebugMessages;

    
    public DebugLoggerAdapter(String name) {
        LoggerContext loggerContext = new ch.qos.logback.classic.LoggerContext();
        this.logger = loggerContext.getLogger(name);

        // Load properties
        Properties properties = new Properties();
        try (InputStream input = DebugLoggerAdapter.class.getClassLoader().getResourceAsStream("dlog4j.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }

        // Get maxDebugMessages from properties
        String maxDebugMessagesProp = properties.getProperty("maxDebugMessages");
        this.maxDebugMessages = Integer.parseInt(maxDebugMessagesProp);
    }

    public int getMaxDebugMessages() {
        return maxDebugMessages;
    }

    public void addAppender(Appender<ILoggingEvent> appender) {
        logger.addAppender(appender);
    }

    public List<String> getDebugMessages() {
        return new ArrayList<>(debugMessages);
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        logger.log(null, DebugLoggerAdapter.class.getName(), Level.TRACE_INT, msg, null, null);
    }

    // Implement the rest of the methods in the same way...

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (debugMessages.size() >= maxDebugMessages) {
            debugMessages.poll();  // remove the oldest message
        }
        debugMessages.add(msg);
        logger.debug(msg);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
       return logger.isTraceEnabled();
    }

    @Override
    public void trace(Marker marker, String msg) {
         logger.trace(marker, msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void debug(String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDebugEnabled'");
    }

    @Override
    public void debug(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public boolean isInfoEnabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInfoEnabled'");
    }

    @Override
    public void info(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInfoEnabled'");
    }

    @Override
    public void info(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public boolean isWarnEnabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isWarnEnabled'");
    }

    @Override
    public void warn(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isWarnEnabled'");
    }

    @Override
    public void warn(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public boolean isErrorEnabled() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isErrorEnabled'");
    }

    @Override
    public void error(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isErrorEnabled'");
    }

    @Override
    public void error(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    
}