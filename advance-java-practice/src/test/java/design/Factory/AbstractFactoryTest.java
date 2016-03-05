package design.Factory;

import lombok.Getter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractFactoryTest {

    private App app = new App();
    @Getter
    private KingdomFactory omKingdomFactory ;
    @Getter
    private KingdomFactory hariKingdomFactory ;


    @Before
    public void setUp() throws Exception {

        omKingdomFactory = app.getOmKingdomFactory();
        hariKingdomFactory = app.getHariKingdomFactory();


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void king() {
        final King elfKing = app.getKing();
        //app.createKingdom(omKingdomFactory);
        assertFalse(elfKing instanceof OmKing);
        assertEquals(new OmKing().Description, new OmKing().getDescription());
       // app.createKingdom(hariKingdomFactory);
        final King hariKing = app.getKing();
        assertFalse(hariKing instanceof HariKing);
        assertEquals(new HariKing().Description, new HariKing().getDescription());
    }
    @Test
    public void createElfKingdom() {
        app.createKingdom(omKingdomFactory);
        final King king = app.getKing();
        final Castle castle = app.getCastle();
        final Army army = app.getArmy();
        assertTrue(king instanceof OmKing);
//        assertEquals(OmKing., king.getDescription());
        assertTrue(castle instanceof OmCastle);
        assertEquals(new OmCastle().Description, castle.getDescription());
        assertTrue(army instanceof OmArmy);
        assertEquals(new OmArmy().Description, army.getDescription());
//        System.out.println(army.Description);
    }
}