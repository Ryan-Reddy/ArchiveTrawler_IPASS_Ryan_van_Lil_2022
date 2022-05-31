package main;

import main.archivetrawler.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User Ryan = new User("Reddy", "ryan@google.com", "Ryan", "Reddy", "PrenticeHall22", "user");
    @BeforeEach
    void setUp() {
        System.out.println("testing USERclass");
    }

    @Test
    void testNewUserCreated(){
        assertEquals("Reddy",Ryan.getUserName());
        assertEquals("ryan@google.com",Ryan.getEmailAdres());
        assertEquals("Ryan",Ryan.getVoorNaam());
        assertEquals("Reddy",Ryan.getAchterNaam());
        assertEquals("PrenticeHall22",Ryan.getWachtwoord());
    }

    @Test
    void testNewUserMustBeUnique() {
        // deze test moet eigenlijk falen, want er is al een gebruiker met deze data
        // TODO zorg er voor dat er geen
        //  twee users kunnen zijn met dezelfde email en gebruikersnaam
        User hank = new User("Reddy", "ryan@google.com","Ryan","Reddy","PrenticeHall22","user");
        assertEquals(null,hank.getUserName());
        assertEquals(null,hank.getEmailAdres());
        assertEquals(null,hank.getVoorNaam());
        assertEquals(null,hank.getAchterNaam());
        assertEquals(null,hank.getWachtwoord());
    }
}
