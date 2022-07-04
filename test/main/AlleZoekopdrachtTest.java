package main;

import archive.trawler.model.User;
import archive.trawler.model.Website;
import archive.trawler.model.Zoekopdracht;
import archive.trawler.model.Archief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlleZoekopdrachtTest {
    Archief archief1 = new Archief("archief1", "www.archief.com/");
    ArrayList<Archief> archievenlisty= new ArrayList<Archief>(Arrays.asList(archief1));
    User testUser = new User("testUser","testUser","testUser");
    Website website = new Website("testwebsite.com/what?helpme",testUser);
    List testWebsitesList = new ArrayList();

//    Zoekopdracht zoek1 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "1", testWebsitesList);
//    Zoekopdracht zoek2 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "2", testWebsitesList);
//    Zoekopdracht zoek3 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "3", testWebsitesList);
//    Zoekopdracht zoek4 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "4", testWebsitesList);
//    later implementatie: complexe zoekqueries
    Zoekopdracht zoek1 = new Zoekopdracht(archievenlisty, "", testUser.getID());
    Zoekopdracht zoek2 = new Zoekopdracht(archievenlisty, "", testUser.getID());
    Zoekopdracht zoek3 = new Zoekopdracht(archievenlisty, "", testUser.getID());
    Zoekopdracht zoek4 = new Zoekopdracht(archievenlisty, "", testUser.getID());

    @BeforeEach
    void setUp() {

    }
    @Test
    public void testZoekopdrachtenUniekOndanksZelfdeInput() {
        assertNotEquals(zoek1,zoek2);
        assertNotEquals(zoek3,zoek4);
    }
}
