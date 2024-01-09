package org.slf4j.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
public class DebugLoggerAdapterTest {
    private Logger logger;

    private ListAppender<ILoggingEvent> listAppender;

    @BeforeEach
    public void setUp() {
        logger = new DebugLoggerAdapter("TestLogger");
        // Create and start a ListAppender
        listAppender = new ListAppender<>();
        listAppender.start();

        // Add the appender to the logger
        ((org.slf4j.impl.DebugLoggerAdapter) logger).addAppender(listAppender);
    }

    @Test
    public void testDebug() {
        String debugMessage = "Debug message";
        logger.debug(debugMessage);

        // Check that the debug message was added to the queue
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.DEBUG && event.getMessage().equals(debugMessage)));
    }

    @Test
    public void testError() {
        String debugMessage = "Debug message";
        String errorMessage = "Error message";
        logger.debug(debugMessage);
        logger.error(errorMessage);

        // Check that the debug message was logged at the error level
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.DEBUG && event.getMessage().equals(debugMessage)));

        // Check that the error message was logged
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.ERROR && event.getMessage().equals(errorMessage)));
    }

    @Test
    public void testMaxDebugMessages() {
        DebugLoggerAdapter castedLogger =  ((org.slf4j.impl.DebugLoggerAdapter) logger);
        // Add maxDebugMessages + 1 messages
        for (int i = 0; i < castedLogger.getMaxDebugMessages() + 1; i++) {
            logger.debug("Debug message " + i);
        }
        String errorMessage = "Error message";
        logger.error(errorMessage);
        // Check that the first message was removed
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.DEBUG && event.getMessage().equals("Debug message 0")));

        // Check that the error message was logged
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.ERROR && event.getMessage().equals(errorMessage)));
    }


    @Test
    public void testOverflowDebugMessages() {
        DebugLoggerAdapter castedLogger =  ((org.slf4j.impl.DebugLoggerAdapter) logger);
        // Add maxDebugMessages + 1 messages
        for (int i = 0; i < castedLogger.getMaxDebugMessages() + 2; i++) {
            logger.debug("Debug message " + i);
        }
        String errorMessage = "Error message";
        logger.error(errorMessage);
        //castedLogger.getDebugMessages().stream()
         //   .forEach(event -> System.out.println(event));    
        // Check that the first message was removed
        assertFalse(castedLogger.getDebugMessages().stream()
            .anyMatch(event ->  event.equals("Debug message 0")));



        // Check that the error message was logged
        assertTrue(listAppender.list.stream()
            .anyMatch(event -> event.getLevel() == Level.ERROR && event.getMessage().equals(errorMessage)));
    }    
}