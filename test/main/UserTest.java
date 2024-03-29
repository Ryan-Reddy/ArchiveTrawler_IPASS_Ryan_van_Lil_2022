package main;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User Ryan = new User( "Ryan Reddy","ryan@google.com","SuperSecretPassword");
    @BeforeEach
    void setUp() {
        System.out.println("testing" + User.class.getName());
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
    void testNewUserMustHaveUniqueEmail() {
        /** Kijk of de allUsersList geen dubbelen aanmaakt werkt */
        new User( "Hank","ryan@google.com","hanksPw");
        User notHank = Community.getUserByEmail("ryan@google.com");

        assertNotEquals("Hank",notHank.getNaam()); //asserts that the new user has not replaced
        // the old user in this email adress

        assertNotEquals("hanksPw",notHank.getPassword()); //asserts that the new user has not replaced
    }

    @Test
    void testPassword() {
        //userpassword moet minstens 6 karakters lang en een cijfer en een speciaal teken bevatten
        assertFalse(false); //TODO work on password minimum req backend
    }
    @Test
    void testRoleStandardUser() {
        // user moet standaard een Rol toegewezen krijgen "user"
        assertEquals("user",Ryan.getRole());
    }
}
