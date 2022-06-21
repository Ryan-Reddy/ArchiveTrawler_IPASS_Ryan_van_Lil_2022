package main;

import archive.trawler.model.Zoekopdracht;
import archive.trawler.model.Archief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlleZoekopdrachtTest {
    Archief archief1 = new Archief("archief1", "www.archief.com/");
    ArrayList<Archief> archievenlisty= new ArrayList<Archief>(Arrays.asList(archief1));
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
