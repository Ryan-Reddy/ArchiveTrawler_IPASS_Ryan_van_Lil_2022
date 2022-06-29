package SendEmail;

import archive.trawler.persistance.Community;
import archive.trawler.webservices.SendEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static archive.trawler.webservices.SendEmail.htmlToString;
import static org.junit.jupiter.api.Assertions.*;

public class TestSendEmailClass {
    String tenminuteMail = "tji32930@jeoce.com";
    String locatieWelkomstMail = "src/main/resources/emailHTMLTemplates/verificationMail.html";

    @BeforeEach
    void setUp() {
        System.out.println("testing class: " + SendEmail.class.getName());
    }

    @Test
    void testEmailLocationFound() {
        Path path = Paths.get(locatieWelkomstMail);
        assertTrue(Files.isReadable(path)); // controleert of deze path vindbaar/leesbaar is
    }
    @Test
    void testEmailSent() throws FileNotFoundException {
        // use tenminute mail for testing    https://10minutemail.net/
        assertTrue(SendEmail.sendMail(tenminuteMail,"testing",locatieWelkomstMail));
    }
    @Test
    void test_MOETFALEN_EmailSentNoException() {
        // Throw word gecatched in methode zelf //
        assertThrows(FileNotFoundException.class, () -> {
            SendEmail.sendMail(tenminuteMail, "testing", "fakeLocation");
        });
    }
    @Test
    void test_MOETFALEN_EmailSentNoException_htmlToString() {
        // TODO Throw word gecatched in de scope methode zelf, test 'faalt' hierdoor helaas nog //
        assertThrows(FileNotFoundException.class, () -> {
            SendEmail.htmlToString("fakeLocation");
        });
    }
}
