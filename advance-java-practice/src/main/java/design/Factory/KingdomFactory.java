package design.Factory;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
public interface KingdomFactory {

    Castle createCastle();
    King   createKing();
    Army   createArmy();
}
