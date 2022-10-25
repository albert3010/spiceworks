package practice_lld_2023.lld_2022.Logger;

import practice_lld_2023.lld_2022.Logger.log.*;
import practice_lld_2023.lld_2022.Logger.observers.*;

public class LoggerManager {

    public static AbstractLogger buildLogger() {
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger errorLogger = new ErrorLogger(2);
        AbstractLogger debugLogger = new DebugLogger(3);
        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        return infoLogger;
    }

    public static LogSubject buildLogSubject() {
        LogSubject logSubject = new LogSubject();

        LogObserver consoleObserver = new ConsoleObserver();
        LogObserver fileObserver = new FileObserver();
        LogObserver databaseObserver = new DatabaseObserver();

        logSubject.addObserver(LogType.INFO, consoleObserver);
        logSubject.addObserver(LogType.INFO, fileObserver);

        logSubject.addObserver(LogType.ERROR, databaseObserver);
        logSubject.addObserver(LogType.ERROR, fileObserver);

        logSubject.addObserver(LogType.DEBUG, fileObserver);

        return logSubject;
    }
}
