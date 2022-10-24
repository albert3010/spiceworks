import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    static AtomicInteger playerID = new AtomicInteger(0);
    static AtomicInteger gameID = new AtomicInteger(0);

    public static int getPlayerID() {
        return playerID.incrementAndGet();
    }

    public static int getGameID() {
        return gameID.incrementAndGet();
    }

}
