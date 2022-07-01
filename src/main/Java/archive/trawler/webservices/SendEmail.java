package archive.trawler.webservices;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;

import java.io.*;
import java.net.URISyntaxException;
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
     * @return boolean of het versturen gelukt is of niet.
     */
    public static boolean sendMail(String sendTo, String subjectLine, String htmlFileFromRoot) throws FileNotFoundException {
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
            // Creeer een message van HTML file keuze.
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
            return true;
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }
        return false;
    }
    /**
     * SendMail haalt een html file op, leest deze in als string en verstuurt deze via gmail naar opgegeven Email adres.
     *
     * @param sendTo           Email adres om naartoe te sturen
     * @param subjectLine      Onderwerp van email
     *                         <br> "src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     * @param token
     * @return boolean of het versturen gelukt is of niet.
     */
    public static boolean sendMailWithToken(String sendTo, String subjectLine, String token) throws FileNotFoundException {
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
            // Creeer een message van HTML file keuze.
            StringBuilder contentBuilder = new StringBuilder();
            try {
//            BufferedReader in = new BufferedReader(new FileReader(htmlFileFromRoot));
                BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\RyRy\\IdeaProjects\\ArchiveTrawler_IPASS_Ryan_van_Lil_2022version200\\src\\main\\resources\\resetmail.html"));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } //TODO move this back to its own package

//            String htmlMessage = htmlToString("archive\\trawler\\webservices\\emailHTMLTemplates\\resetmail.html");
//            System.out.println(htmlMessage);
            String htmlMessage = contentBuilder.toString().replace("token", token); //TODO write recipient for sessionstorage token

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
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;
    }

    /** Leest html file, retourneert String, denk erom om met exape characters te programmeren.
     * @param htmlFileFromRoot Html filename, as string incl. path from source root example:
     *
     *                         "./src/main/Java/archive/trawler/webservices/emailHTMLTemplates/verificationMail.html"
     * @return String met contents
     */
    public static String htmlToString(String htmlFileFromRoot) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try {
//            BufferedReader in = new BufferedReader(new FileReader(htmlFileFromRoot));
            BufferedReader in = new BufferedReader(new FileReader("/resetmail.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
//        StringBuilder contentBuilder = new StringBuilder();
//        try {
////            BufferedReader in = new BufferedReader(new FileReader(htmlFileFromRoot));
//            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/resetmail.html"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                contentBuilder.append(str);
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(contentBuilder.toString());
        SendEmail.sendMailWithToken("email@email.email", "Uw wachtwoord reset link.","token");

//        System.out.println(htmlToString("\\resetmail.html"));
//        System.out.println(htmlToString("src\\main\\Java\\archive\\trawler\\webservices\\emailHTMLTemplates\\resetmail.html"));

        // Create a file object
        File f = new File("src/main/Java/archive/trawler/webservices/emailHTMLTemplates/program.txt");

        // Get the absolute path of file f
        String absolute = f.getAbsolutePath();

        // Display the file path of the file object
        // and also the file path of absolute file
        System.out.println("Original  path: "
                + f.getPath());
        System.out.println("Absolute  path: "
                + absolute);
        }
    }

