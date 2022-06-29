package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
//import nl.hu.bep.referenceproject.model.Account;
//import nl.hu.bep.referenceproject.model.Company;
//import nl.hu.bep.referenceproject.persistence.EncodedBase64;
//import nl.hu.bep.referenceproject.persistence.UploadsManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/accounts")
public class AccountsResourceFullJackson {
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccountFullJackson(User account) {
        Map<String, String> messages = new HashMap<>();
        if (Community.getCommunity().addUserToMap(account)) {
//            if (!account.getAvatarBase64().isEmpty()) { // alleen nodig als ik een file wil koppelen aan User
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.uploadToAzure(base64); // upload Id is de unieke blob voor deze upload
//                account.setAvatarUploadId(uploadId);
//            }
            messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + account.getNaam());
            String naam = account.getNaam();
            SendEmail.sendMail(account.getEmail(),  "welkom to ArchiveTrawler! We're happy to have you!", "src/main/resources/emailHTMLTemplates/verificationMail.html");
            return Response.ok(messages).build();
        } else {
            messages.put("Error", "Er klopt iets niet aan uw email!");
            return Response.status(Response.Status.CONFLICT).entity(messages).build();
        }
    }
//
//
//    /**
//     * @param sendTo Email adres om naartoe te sturen
//     * @param subjectLine Onderwerp van email
//     * @param htmlMessage Inhoud van mail in HTML
//     */
//    public static void sendMail(String sendTo, String subjectLine, String htmlMessage) {
//        String from = "no_reply@archive-trawler.com";        // Sender's email ID needs to be mentioned
//        String host = "smtp.gmail.com";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//        // Get the Session object.// and pass username and password
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication("archive.trawler.notification@gmail.com", "dxzukkfaevoxekmg");
//
//            }
//
//        });
//
//        // Used to debug SMTP issues
//        session.setDebug(true);
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
//
//            // Set Subject: header field
//            message.setSubject(subjectLine);
//
//            // Send the actual HTML message.
//            message.setContent(htmlMessage+
//                            "<h1>This is actual message embedded in HTML tags</h1>",
//                    "text/html");
//
//            System.out.println("sending...");
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
}

