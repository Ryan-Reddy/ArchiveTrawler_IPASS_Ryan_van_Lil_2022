package archive.trawler.webservices;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Deze klasse zorgt ervoor dat ik een email kan sturen met javax vanuit de backend
 */
public class SendEmail {

    /**
     * @param sendTo Email adres om naartoe te sturen
     * @param subjectLine Onderwerp van email
     * @param htmlMessage Inhoud van mail in HTML
     */
    public void sendMail(String sendTo, String subjectLine, String htmlMessage) {
        String from = "no_reply@archive-trawler.com";        // Sender's email ID needs to be mentioned
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("archive.trawler.notification@gmail.com", "dxzukkfaevoxekmg");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

            // Set Subject: header field
            message.setSubject(subjectLine);

            // Send the actual HTML message.
            message.setContent(htmlMessage+
                    "<h1>This is actual message embedded in HTML tags</h1>",
                    "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
