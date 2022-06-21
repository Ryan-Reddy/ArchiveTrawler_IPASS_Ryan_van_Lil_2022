//package MyUser;
//
//import archive.trawler.security.MyUser;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class MyUserTest {
//    private String testMail = "ryan@google.com";
//    private String testPassword = "PrenticeHall22";
//    private String testName = "Ryan";
//
//    Boolean bool = new MyUser(testMail, testPassword, testName);
//    MyUser testee = (MyUser) MyUser.getUserByEmail(testMail);
//
//    @BeforeEach
//    void setUp() {
//        System.out.println("testing MyUserClass");
//    }
//
//    @Test
//    void testNewUserCreatedEmail_True(){
//        assertEquals(testMail,testee.getEmail());
//    }
//    @Test
//    void testNewUserCreatedRole_True(){
//        assertEquals("user",testee.getRole());
//    }
//    @Test
//    void testNewUserCreatedName_True(){
//        assertEquals(testName,testee.getNaam());
//    }
//
//    @Test
//    void testNewUserMustBeUnique_False() {
//        // deze test moet eigenlijk falen, want er is al een gebruiker met deze data
//        // TODO zorg er voor dat er geen
//        //  twee users kunnen zijn met dezelfde email en gebruikersnaam
//        Boolean testee2 = MyUser.registerUser(testMail, testPassword, "andereNaam");
//        assertNotEquals("andereNaam",MyUser.getUserByEmail(testMail).getNaam());
//    }
//
//    @Test
//    void testPasswordIsLangGenoeg_False(){
//        //userpassword moet minstens 6 karakters lang en een cijfer en een speciaal teken bevatten
//        assertEquals(false, MyUser.registerUser("Langgenoeg@gmail.com","kort","Abraham Moskovicz"));
//        assertEquals(false, MyUser.registerUser("Langgenoeg@gmail.com","helelangewachtwroood","Abraham Moskovicz"));
//    }
//
//    @Test
//    void testEmailIsEchtEmail_False(){
//        //userpassword moet minstens 6 karakters lang
//        assertEquals(false, MyUser.registerUser("Langgenoeg@gmail.com","helelangeWachtwoord","Abraham Moskovicz"));
//    }
//
//    @AfterAll
//    void clearAll() {
//        MyUser.deleteMyUserAccount(testMail);
//    }
//}