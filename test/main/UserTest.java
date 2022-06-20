package main;

import archive.trawler.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User Ryan = new User("ryan@google.com", "Ryan Reddy");
    @BeforeEach
    void setUp() {
        System.out.println("testing USERclass");
    }
    @Test
    void testNewUserCreated() {
        System.out.println(Ryan);
        assertEquals("ryan@google.com", Ryan.getEmailAdres());
        assertEquals("Ryan Reddy", Ryan.getName());
    }
    @Test
    void testGetUserByEmail() {
        assertEquals(Ryan, User.getUserByEmail("ryan@google.com"));
        assertEquals("Ryan Reddy", Ryan.getName());
    }
    @Test
    void testNewUserMustBeUnique() {
        // deze test moet eigenlijk falen, want er is al een gebruiker met deze data
        // TODO zorg er voor dat er geen
        //  twee users kunnen zijn met dezelfde email en gebruikersnaam
        User hank = new User("ryan@google.com", "Ryan Reddy");
        assertNull(hank.getEmailAdres());
        assertNull(hank.getName());
        assertNull(hank.getRole());
    }
    @Test
    void testUserList() {
        /** Kijk of de allUsersList goed werkt */
        User hank = new User("ryan@google.com", "Ryan");
        assertNull(User.getAllUsers());
    }
    @Test
    void testPassword() {
        //userpassword moet minstens 6 karakters lang en een cijfer en een speciaal teken bevatten
        assertFalse(true);
    }
}
