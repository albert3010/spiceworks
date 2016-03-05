package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public class HariKingdomFactory implements KingdomFactory {

    public Castle createCastle(){
        return new HariCastle();
    }
    public King createKing(){
        return new HariKing();
    }
    public Army createArmy(){
        return new HariArmy();
    }
}
