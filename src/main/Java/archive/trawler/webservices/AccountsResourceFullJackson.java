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
import java.io.IOException;
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
        if (Community.addUserToMap(account)) {
            /* alleen nodig als ik een file wil koppelen aan User*/
//            if (!account.getAvatarBase64().isEmpty()) {
//        }
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.uploadToAzure(base64); // upload Id is de unieke blob voor deze upload
//                account.setAvatarUploadId(uploadId);
//            }
            messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + account.getNaam());
            String naam = account.getNaam();
            SendEmail.sendMail(account.getEmail(), "welkom to ArchiveTrawler! We're happy to have you!", "verificationMail.html");
            return Response.ok(messages).build();// TODO remove absolute path to maybe relative? its in a resource folder..
        } else {
            messages.put("Error", "Er klopt iets niet aan uw email!");
            return Response.status(Response.Status.CONFLICT).entity(messages).build();
        }
    }
}

