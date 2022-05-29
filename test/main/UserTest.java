package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User Ryan = new User("Reddy", "ryan@google.com","Ryan","Reddy","PrenticeHall22");
    @BeforeEach
    void setUp() {
        System.out.println("testing USERclass");
    }

    @Test
    void testNewUserCreated(){
        assertEquals(Ryan.getUserName(),"Reddy");
        assertEquals(Ryan.getEmailAdres(),"ryan@google.com");
        assertEquals(Ryan.getVoorNaam(),"Ryan");
        assertEquals(Ryan.getAchterNaam(),"Reddy");
        assertEquals(Ryan.getWachtwoord(),"PrenticeHall22");
    }

    @Test
    void testNewUserMustBeUnique() {
        // deze test moet eigenlijk falen, want er is al een gebruiker met deze data
        // TODO zorg er voor dat er geen
        //  twee users kunnen zijn met dezelfde email en gebruikersnaam
        User hank = new User("Reddy", "ryan@google.com","Ryan","Reddy","PrenticeHall22");
        assertEquals(hank,null);
    }
}
