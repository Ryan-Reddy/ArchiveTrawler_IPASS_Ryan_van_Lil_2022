package archive.trawler.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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
            return Response.ok(messages).build();
        } else {
            messages.put("Error", "Er klopt iets niet aan uw email!");
            return Response.status(Response.Status.CONFLICT).entity(messages).build();
        }
    }
}

