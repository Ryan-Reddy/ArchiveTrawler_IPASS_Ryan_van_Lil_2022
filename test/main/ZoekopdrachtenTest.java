package main;

import main.archivetrawler.model.Archieven;
import main.archivetrawler.model.Zoekopdrachten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ZoekopdrachtenTest {
    Archieven archief1 = new Archieven("archief1", "www.archief.com/");
    ArrayList<Archieven> archievenlisty= new ArrayList<Archieven>(Arrays.asList(archief1));
    Zoekopdrachten zoek1 = new Zoekopdrachten(archievenlisty, "", "", "", 1800, 1900, "", "1");
    Zoekopdrachten zoek2 = new Zoekopdrachten(archievenlisty, "", "", "", 1800, 1900, "", "2");
    Zoekopdrachten zoek3 = new Zoekopdrachten(archievenlisty, "", "", "", 1800, 1900, "", "3");
    Zoekopdrachten zoek4 = new Zoekopdrachten(archievenlisty, "", "", "", 1800, 1900, "", "4");

    @BeforeEach
    void setUp() {
    }
    @Test
    public void testZoekopdrachtenUniekOndanksZelfdeInput() {
        assertNotEquals(zoek1,zoek2);
        assertNotEquals(zoek3,zoek4);
    }
}
