package main;

import archive.trawler.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User Ryan = new User( "Ryan Reddy","ryan@google.com","SuperSecretPassword");
    @BeforeEach
    void setUp() {
        System.out.println("testing USERclass");
    }
    @Test
    void testNewUserCreated() {
        System.out.println(Ryan);
        assertEquals("ryan@google.com", Ryan.getEmail());
        assertEquals("Ryan Reddy", Ryan.getNaam());
    }
    @Test
    void testGetUserByEmail() {
        assertEquals(Ryan, User.getUserByEmail("ryan@google.com"));
        assertEquals("Ryan Reddy", Ryan.getNaam());
    }
    @Test
    void testNewUserMustBeUnique() {
        // deze test moet eigenlijk falen, want er is al een gebruiker met deze data
        // TODO zorg er voor dat er geen
        //  twee users kunnen zijn met dezelfde email en gebruikersnaam
        User hank = new User("ryan@google.com", "Ryan Reddy","secret");
        assertNull(hank.getEmail());
        assertNull(hank.getNaam());
        assertNull(hank.getRole());
    }
    @Test
    void testUserList() {
        /** Kijk of de allUsersList goed werkt */
        User hank = new User("ryan@google.com", "secondUser","sameEmail");
        assertNull(hank);
    }
    @Test
    void testPassword() {
        //userpassword moet minstens 6 karakters lang en een cijfer en een speciaal teken bevatten
        assertFalse(true);
    }
}
