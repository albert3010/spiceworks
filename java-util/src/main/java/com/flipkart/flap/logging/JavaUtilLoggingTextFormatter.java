package com.flipkart.flap.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by omprakash.yadav on 20/02/16.
 */
public class JavaUtilLoggingTextFormatter extends Formatter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");


    public JavaUtilLoggingTextFormatter()
    {
        super();
    }

    public String format(LogRecord record){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        String loggerName = record.getLoggerName();
        Throwable ex    = record.getThrown();

        String message = super.formatMessage(record);

        pw.print (DATE_FORMAT.format (new Date(record.getMillis())));
        pw.print (' ');
        pw.print (record.getLevel().toString());
        pw.print (" (");

        int i = loggerName.lastIndexOf (".");

        if ((i != -1) && (i < (loggerName.length() - 1)))
            loggerName = loggerName.substring (i + 1);

        pw.print(loggerName);
        pw.print(")");

        if ((message != null) && (message.trim().length() > 0))
            pw.println (message);

        if (ex != null)
            ex.printStackTrace (pw);

        return sw.toString();

    }
}
