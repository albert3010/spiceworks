package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class OmKingdomFactory implements KingdomFactory {

    public Castle createCastle() {
        return new OmCastle();
    }

    public King createKing() {
        return new OmKing();
    }

    public Army createArmy() {
        return new OmArmy();
    }
}
