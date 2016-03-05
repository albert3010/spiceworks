package samplLog;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by omprakash.yadav on 21/02/16.
 */
public class Log4jSample {

    static Logger log = Logger.getLogger(Log4jSample.class.getName());

    public static void main(String[] args) throws IOException,SQLException {

        BasicConfigurator.configure();
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
    }
}
