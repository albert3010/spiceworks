package design.Factory;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by omprakash.yadav on 19/02/16.
 */
@Setter
@Getter
public class App {

    private King king;
    private Castle castle;
    private Army army;


    /**
     * Creates kingdom
     */
    public void createKingdom(final KingdomFactory factory) {
        setKing(factory.createKing());
        setCastle(factory.createCastle());
        setArmy(factory.createArmy());
    }

    OmKingdomFactory getOmKingdomFactory() {return new OmKingdomFactory(); }

    HariKingdomFactory getHariKingdomFactory() {
        return new HariKingdomFactory();
    }

    public static void main(String[] args) {
        App app = new App();

        System.out.println(args.length);
        System.out.println("Om Kingdom..");
        KingdomFactory OmKingdomFactory;
        OmKingdomFactory = app.getOmKingdomFactory();
        app.createKingdom(OmKingdomFactory);
        System.out.println(app.getArmy().getDescription());
        System.out.println(app.getCastle().getDescription());
        System.out.println(app.getKing().getDescription());

        System.out.println("\nHari Kingdom..");
        KingdomFactory HariKingdomFactory;
        HariKingdomFactory = app.getHariKingdomFactory();
        app.createKingdom(HariKingdomFactory);
        System.out.println(app.getArmy().getDescription());
        System.out.println(app.getCastle().getDescription());
        System.out.println(app.getKing().getDescription());

    }
}
