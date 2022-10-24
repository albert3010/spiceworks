package practice_lld_2023.lld_2022.Logger.log;

public enum LogType {
    INFO(1),
    ERROR(2),
    DEBUG(3);

    private int level;

    LogType(int level) {
        this.level = level;
    }
     public int getLevel(){
        return level;
    }
}
