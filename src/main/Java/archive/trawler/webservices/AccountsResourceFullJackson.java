package archive.trawler.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.persistance.UploadsManager;
import archive.trawler.security.EncodedBase64;
//import nl.hu.bep.referenceproject.model.Account;
//import nl.hu.bep.referenceproject.model.Company;
//import nl.hu.bep.referenceproject.persistence.EncodedBase64;
//import nl.hu.bep.referenceproject.persistence.UploadsManager;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static javax.ws.rs.core.Response.ok;

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
            SendEmail.sendMail(account.getEmail(),  naam + ", welcome to ArchiveTrawler! We zijn blij je erbij te hebben!",
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\"/>\n" +
                            "    <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/>\n" +
                            "    <meta content=\"ie=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                            "    <link href=\"/style-index.css\" rel=\"stylesheet\"/>\n" +
                            "\n" +
                            "    <title>Bevestig account</title>\n" +
                            "    <script src=\"/js/themeManager.js\" type=\"text/javascript\"></script>\n" +
                            "</head>\n" +
                            "<h1 id=\"header\">Hoi " + naam + " </h1>\n" +
                            "<body class=\"bodyDark\" id=\"body\">\n" +
                            "</div>\n" +
                            "<main>\n" +
                            "    Klik hier om uw email voor dit account te bevestigen:\n" +
                            "    <span id=\"accountInfoSpan\"></span>\n" +
                            "        <label for=\"bevestigButton\" hidden>account te bevestigen button</label>\n" +
                            "        <input type=\"button\" id=\"bevestigButton\" onclick=\"window.open('')\" value=\"Activeer mijn account\" style=\"background-color: darkmagenta\">\n" + // TODO insert proper link that actually sets UserActivation attribute on Activated
                            "</main>\n" +
                            "    <footer id=\"footer\">\n" +
                            "    </footer>\n" +
                            "</body>\n" +
                            "</html>");
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

