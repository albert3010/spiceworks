package design.adapter;

/**
 * Created by omprakash.yadav on 26/02/16.
 */

/**
 * The Captain uses {@link BattleShip} to fight. <br>
 * This is the client in the pattern.
 */
public class BattleFishingBoat implements BattleShip {

    private FishingBoat boat = new FishingBoat();

    public BattleFishingBoat(String s) {
        boat = new FishingBoat();
    }
    @Override
    public void move(){
       boat.sail();
        System.out.println("moving");
    }
    @Override
    public void fire(){
        System.out.println("firing");
    }


}
