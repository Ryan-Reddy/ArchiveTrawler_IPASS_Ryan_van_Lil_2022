package archive.trawler.webservices;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Deze klasse zorgt ervoor dat ik een email kan sturen met javax vanuit de backend
 */
public class SendEmail {

    /**
     * SendMail haalt een html file op, leest deze in als string en verstuurt deze via gmail naar opgegeven Email adres.
     *
     * @param sendTo           Email adres om naartoe te sturen
     * @param subjectLine      Onderwerp van email
     * @param htmlFileFromRoot Html filename, as string incl. path from source root example:
     *                         <br> "src/main/resources/emailHTMLTemplates/verificationMail.html"
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

            protected PasswordAuthentication getPasswordAuthentication() { //login gmail
                return new PasswordAuthentication("archive.trawler.notification@gmail.com", "dxzukkfaevoxekmg");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Creeer een bericht van HTML file keuze.
            String htmlMessage = htmlToString(htmlFileFromRoot);

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

    /**
     * SendMail haalt een html file op, leest deze in als string en verstuurt deze via gmail naar opgegeven Email adres.
     *
     * @param sendTo      Email adres om naartoe te sturen
     * @param subjectLine Onderwerp van email
     *                    <br> "src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     * @param token
     */
    public static void sendMailWithToken(String sendTo, String subjectLine, String token) throws IOException {
        System.out.println("received request to sendMailWithToken");
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
            protected PasswordAuthentication getPasswordAuthentication() { //login op gmail:
                return new PasswordAuthentication("archive.trawler.notification@gmail.com", "dxzukkfaevoxekmg");
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            //String fileName = (resources/) implied "resetmail.html";
            String fileName = "resetmail.html";


            String htmlMessage = htmlToString(fileName);
            htmlMessage = htmlMessage.replace("<<TOKEN>>", token); //TODO write recipient for sessionstorage token

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

    /**
     * Leest html file, retourneert String, denk erom om met exape characters te programmeren.
     *
     * @param fileName Html filename, als string vanuit resources:
     *                 <p>
     *                 "./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     * @return String met contents
     */
    public static String htmlToString(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();

        // The class loader that loaded the class
        ClassLoader classLoader = SendEmail.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            try (InputStreamReader streamReader =
                         new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    contentBuilder.append(line);
                }
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return contentBuilder.toString();
        }
    }
}






