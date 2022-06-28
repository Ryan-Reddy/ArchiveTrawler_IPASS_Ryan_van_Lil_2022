package main;

import archive.trawler.model.Archief;
import archive.trawler.persistance.Community;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArchiefTest {
    Archief testArchief1 = new Archief("testArchief1","www.test.com/");
    Archief testArchief2 = new Archief("testArchief2","www.test.com/");

    @BeforeEach
    void setUp() {
        System.out.println("testing" + Archief.class.getName());
    }
    @Test
    void testNewArchiefCreated() {
        System.out.println("testNewArchiefCreated");
        System.out.println(testArchief1);
        assertEquals("www.test.com/", testArchief1.getBasisURI());
        assertEquals("testArchief1", testArchief1.getNaam());
    }
    @Test
    void testNewArchiefMustHaveUniqueName() {
        /** Kijk of de archiefMap geen dubbelen URI accepteert */
        Archief testArchief1Extra = new Archief("testArchief","www.testextra.com/");

//        Archief archiefUitPersistance = Community.getArchiefByName("testArchief1");
//        Archief archiefUitPersistanceByURI = Community.getArchiefByURI("www.testextra.com/");

//        assertEquals("www.test.com/",archiefUitPersistance.getBasisURI()); //asserts that the new user has not replaced
        assertEquals("www.test.com/",Community.getArchiefByName("testArchief1").getBasisURI()); //asserts that the new user has not replaced
        assertNull( Community.getArchiefByURI("www.testextra.com/")); //asserts that the new user has not replaced
        // TODO splits deze testcase
    }
}
