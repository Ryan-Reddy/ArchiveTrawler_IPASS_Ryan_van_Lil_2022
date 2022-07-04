package main;

import archive.trawler.model.User;
import archive.trawler.model.Website;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsitesTest {
    User testuser = new User("","","");
        Website websites1 = new Website("www.ttt.ttt/honoluluGold", testuser);
        Website websites2 = new Website("www.ttt.ttt/honoluluGold", testuser);
        Website websites3 = new Website("www.ttt.ttt/honoluluGold", testuser);

    @BeforeEach
    void setUp() {
        System.out.println("testing" + Website.class.getName());

    }

    @Test
    void testMeerdereSitesVerschillendeGebruikersMogelijk() {
        // test dat meerdere websites met verschillende gebruikers kunnen bestaan.
        assertNotNull(websites1);
        assertNotNull(websites2);
        assertNotNull(websites3);
    }

    @Test
    void testBekekenDataPerKeerBijgewerkt() {
        // test het tellen van aantal keer aangeklikt.
        websites1.websiteDoorgelinkt();
        assertEquals(1,websites1.getAantalKeerBezocht());
        websites1.websiteDoorgelinkt();
        assertEquals(2,websites1.getAantalKeerBezocht());
        websites1.websiteDoorgelinkt();
        websites1.websiteDoorgelinkt();
        assertEquals(4,websites1.getAantalKeerBezocht());
    }

    @Test
    void testBekekenDatumLogGroeit() {
        // test dat de datum lijst groeit per klik.
        websites1.websiteDoorgelinkt();
        assertEquals(1,websites1.getDatumBezocht().size());
        websites1.websiteDoorgelinkt();
        assertEquals(2,websites1.getDatumBezocht().size());
    }
}
