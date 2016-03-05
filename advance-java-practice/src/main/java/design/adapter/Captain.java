package design.adapter;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by omprakash.yadav on 26/02/16.
 */
public class Captain implements BattleShip{

    @Setter
    private BattleShip battleShip;

    public Captain(BattleShip battleShip){
        this.battleShip = battleShip ;
    }
    @Override
    public void fire(){
        battleShip.fire();
    }
    public void move(){
        battleShip.move();
    }

}
