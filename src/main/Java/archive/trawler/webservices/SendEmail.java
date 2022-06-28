package archive.trawler.webservices;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Deze klasse zorgt ervoor dat ik een email kan sturen met javax vanuit de backend
 */
public class SendEmail {

    /** SendMail haalt een html file op, leest deze in als string en verstuurt deze via gmail naar opgegeven Email adres.
     * @param sendTo Email adres om naartoe te sturen
     * @param subjectLine Onderwerp van email
     * @param htmlFileFromRoot Html filename, as string incl. path from source root example:
     * <br> "./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     */
    public static void sendMail(String sendTo, String subjectLine, String htmlFileFromRoot) {
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
            // Creeer een message van HTML file keuze.
            String htmlMessage = htmlToString("./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html");

            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));

            // Set Subject: header field
            message.setSubject(subjectLine);

            // Send the actual HTML message.
            message.setContent(htmlMessage,
                    "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /** Leest html file, retourneert String, denk erom om met exape characters te programmeren.
     * @param htmlFileFromRoot Html filename, as string incl. path from source root example:
     *
     *                         "./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     * @return String met contents
     */
    private static String htmlToString(String htmlFileFromRoot){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(htmlFileFromRoot));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String content = contentBuilder.toString();
        return content;
    }
//    public static void main(String[] args) {
//        System.out.println("what about that huh");
//        System.out.println(htmlToString("./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"));
//    }
}
