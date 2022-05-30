package main;

import main.archivetrawler.model.Websites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsitesTest {
        Websites websites1 = new Websites("www.ttt.ttt/honoluluGold", "111");
        Websites websites2 = new Websites("www.ttt.ttt/honoluluGold","222");
        Websites websites3 = new Websites("www.ttt.ttt/honoluluGold","333");

    @BeforeEach
    void setUp() {
    }

    @Test
    void testMeerdereSitesVerschillendeGebruikersMogelijk() {
        // test dat meerdere websites met verschillende gebruikers kunnen bestaan.
        assertNotNull(websites1);
        assertNotNull(websites2);
        assertNotNull(websites3);
    }

    @Test
    void testMeerdereWebsitesZelfdeURLTochVerschillend() {
        // test dat meerdere websites met verschillende gebruikers echt verschillende objecten zijn.
        assertNotEquals(websites1,websites2);
        assertNotEquals(websites2,websites3);
        assertNotEquals(websites3,websites1);
    }

    @Test
    void testBekekenDataPerKeerBijgewerkt() {
        // test het tellen van aantal keer aangeklikt.
        websites1.websiteDoorgelinkt();
        assertEquals(1,websites1.getAantalKeerBezocht());
        websites1.websiteDoorgelinkt();
        assertEquals(2,websites1.getAantalKeerBezocht());
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
