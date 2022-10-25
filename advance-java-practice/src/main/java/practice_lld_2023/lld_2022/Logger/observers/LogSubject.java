package practice_lld_2023.lld_2022.Logger.observers;

import practice_lld_2023.lld_2022.Logger.log.LogType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSubject {

    Map<LogType, List<LogObserver>> logTypeObservers;

    public LogSubject() {
        this.logTypeObservers = new HashMap<>();
    }

    public void addObserver(LogType logType, LogObserver logObserver) {
        List<LogObserver> logObservers = logTypeObservers.getOrDefault(logType, new ArrayList<>());
        logObservers.add(logObserver);
        logTypeObservers.put(logType, logObservers);
    }

    public void notifyAll(LogType logType, String message) {
        for (LogObserver logObserver : logTypeObservers.get(logType)) {
            logObserver.log(message);
        }
    }
}
