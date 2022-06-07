package main;

import trawler.model.Archieven;
import trawler.model.Zoekopdracht;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ZoekopdrachtTest {
    Archieven archief1 = new Archieven("archief1", "www.archief.com/");
    ArrayList<Archieven> archievenlisty= new ArrayList<Archieven>(Arrays.asList(archief1));
    Zoekopdracht zoek1 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "1");
    Zoekopdracht zoek2 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "2");
    Zoekopdracht zoek3 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "3");
    Zoekopdracht zoek4 = new Zoekopdracht(archievenlisty, "", "", "", 1800, 1900, "", "4");

    @BeforeEach
    void setUp() {
    }
    @Test
    public void testZoekopdrachtenUniekOndanksZelfdeInput() {
        assertNotEquals(zoek1,zoek2);
        assertNotEquals(zoek3,zoek4);
    }
}
