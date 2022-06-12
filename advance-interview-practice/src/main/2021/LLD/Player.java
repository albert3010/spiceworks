
public class Player {

    int id;
    String name;

    public Player(String name) {
        this.id = Constants.getPlayerID();
        this.name = name;
    }

    int getDiceNo(){
      return 2;
    }
}
