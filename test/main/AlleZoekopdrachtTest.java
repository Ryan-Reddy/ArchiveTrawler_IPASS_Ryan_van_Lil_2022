package main;

import trawler.model.Archief;
import trawler.model.AlleZoekopdracht;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlleZoekopdrachtTest {
    Archief archief1 = new Archief("archief1", "www.archief.com/");
    ArrayList<Archief> archievenlisty= new ArrayList<Archief>(Arrays.asList(archief1));
    AlleZoekopdracht zoek1 = new AlleZoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "1");
    AlleZoekopdracht zoek2 = new AlleZoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "2");
    AlleZoekopdracht zoek3 = new AlleZoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "3");
    AlleZoekopdracht zoek4 = new AlleZoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "4");

    @BeforeEach
    void setUp() {
    }
    @Test
    public void testZoekopdrachtenUniekOndanksZelfdeInput() {
        assertNotEquals(zoek1,zoek2);
        assertNotEquals(zoek3,zoek4);
    }
}
