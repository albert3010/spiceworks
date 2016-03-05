package com.flipkart.flap.logging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by omprakash.yadav on 20/02/16.
 */
public class Logger {

        // Public Constants

    public static LogLevel LEVEL_DEBUG = LogLevel.DEBUG;

    public static LogLevel LEVEL_ERROR = LogLevel.ERROR;

    public static LogLevel LEVEL_WARNING = LogLevel.WARNING;

    public static LogLevel LEVEL_FATAL = LogLevel.FATAL;

    public static LogLevel LEVEL_INFO = LogLevel.INFO;

    public static LogLevel LEVEL_TRACE = LogLevel.TRACE;

    private String className = null;

    private org.apache.commons.logging.Log realLogger = null;

    private static Collection<Logger> loggers = new ArrayList<Logger>();


    /**
     * Whether or not logging is enabled.
     */
    private static boolean enabled = false;

    public Logger(String className){

        this.className = className;

        synchronized (loggers){
            loggers.add(this);

            if (enabled)
                enableLogger (this);
        }

        }

    public Logger (Class cls)
    {
        this (cls.getName());
    }

    public static void enableLogging() throws  UnsupportedOperationException{

        synchronized (loggers){
            if (! enabled){

                for(Logger logger : loggers){

                    enableLogger(logger);
                    enabled = true;
                }
            }
        }
    }
    /**
     * Log a message with debug log level.
     *
     * @param message  The message to convert to a string and log
     */
    public void debug (Object message)
    {
        if (realLogger != null)
            realLogger.debug (message.toString());
    }


    /**
     * Log an error with debug log level.
     *
     * @param message  The message to convert to a string and log
     * @param ex       The exception to log with the message
     *
     */
    public void debug (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.debug (message.toString(), ex);
    }

    public void error (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.error (message.toString(), ex);
    }

    public void error(Object massage){

        if(realLogger != null)
            realLogger.error(massage.toString());

    }

    /**
     * Log a message with fatal log level.
     *
     * @param message  The message to convert to a string and log
     */
    public void fatal (Object message)
    {
        if (realLogger != null)
            realLogger.fatal (message.toString());

    }

    /**
     * Log an error with fatal log level.
     *
     * @param message  The message to convert to a string and log
     * @param ex       The exception to log with the message
     *
     */
    public void fatal (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.fatal (message.toString(), ex);
    }

    /**
     * Log a message with info log level.
     *
     * @param message  The message to convert to a string and log
     */
    public String info (Object message)
    {
        if (realLogger != null)
            realLogger.info (message.toString());
        return message.toString();
    }

    /**
     * Log an error with info log level.
     *
     * @param message  The message to convert to a string and log
     * @param ex       The exception to log with the message
     *
     */
    public void info (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.info (message.toString(), ex);
    }

    /**
     * Log a message with trace log level.
     *
     * @param message  The message to convert to a string and log
     */
    public void trace (Object message)
    {
        if (realLogger != null)
            realLogger.trace (message.toString());
    }

    /**
     * Log an error with trace log level.
     *
     * @param message  The message to convert to a string and log
     * @param ex       The exception to log with the message
     *
     */
    public void trace (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.trace (message.toString(), ex);
    }

    /**
     * Log a message with warn log level.
     *
     * @param message  The message to convert to a string and log
     */
    public void warn (Object message)
    {
        if (realLogger != null)
            realLogger.warn (message.toString());
    }

    /**
     * Log an error with warn log level.
     *
     * @param message  The message to convert to a string and log
     * @param ex       The exception to log with the message
     *
     */
    public void warn (Object message, Throwable ex)
    {
        if (realLogger != null)
            realLogger.warn (message.toString(), ex);
    }


    public void massage(LogLevel level , Object message){


        switch (level){
            case DEBUG:
                debug (message);
                break;

            case ERROR:
                error (message);
                break;

            case FATAL:
                fatal (message);
                break;

            case INFO:
                info (message);
                break;

            case TRACE:
                trace (message);
                break;

            case WARNING:
                warn (message);
                break;

            default:
                assert (false);
        }
    }
    public void message (LogLevel level, Object message, Throwable ex)
    {
        switch (level)
        {
            case DEBUG:
                debug (message, ex);
                break;

            case ERROR:
                error (message, ex);
                break;

            case FATAL:
                fatal (message, ex);
                break;

            case INFO:
                info (message, ex);
                break;

            case TRACE:
                trace (message, ex);
                break;

            case WARNING:
                warn (message, ex);
                break;

            default:
                assert (false);
        }
    }

    public boolean isDebugEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isDebugEnabled();
    }

    public boolean isErrorEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isErrorEnabled();
    }

    public boolean isFatalEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isFatalEnabled();
    }

    /**
     * Determine whether info logging is currently enabled.
     *
     * @return <tt>true</tt> if info logging is enabled,
     *         <tt>false</tt> otherwise
     */
    public boolean isInfoEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isInfoEnabled();
    }

    /**
     * Determine whether trace logging is currently enabled.
     *
     * @return <tt>true</tt> if trace logging is enabled,
     *         <tt>false</tt> otherwise
     */

    public boolean isTraceEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isTraceEnabled();
    }

    public boolean isWarningEnabled()
    {
        return (realLogger == null) ? false
                : realLogger.isWarnEnabled();
    }


    private static void enableLogger (Logger logger) throws UnsupportedOperationException {
        synchronized (logger)
        {
            if (logger.realLogger == null)
                logger.realLogger = LogFactory.getLog(logger.className);
        }
    }

}