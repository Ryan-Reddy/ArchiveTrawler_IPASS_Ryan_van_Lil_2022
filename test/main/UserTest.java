package main;

import trawler.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User Ryan = new User( "ryan@google.com", "Ryan", "Reddy", "PrenticeHall22", "user");
    @BeforeEach
    void setUp() {
        System.out.println("testing USERclass");
    }

    @Test
    void testNewUserCreated(){
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
        User hank = new User("ryan@google.com","Ryan","Reddy","PrenticeHall22","user");
        assertEquals(null,hank.getEmailAdres());
        assertEquals(null,hank.getVoorNaam());
        assertEquals(null,hank.getAchterNaam());
        assertEquals(null,hank.getWachtwoord());
    }
    @Test
    void testPassword(){
        //userpassword moet minstens 6 karakters lang en een cijfer en een speciaal teken bevatten
        assertFalse(true);
    }
}
