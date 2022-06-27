package archive.trawler.webservices;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/accounts")
public class AccountsResourceFullJackson {
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccountHalfJackson(String jsonBody) {
//        Account account = new Account();
//
//        try {
//            JsonObject object = Json.createReader(new StringReader(jsonBody)).readObject();
//            account.setUsername(object.getString("username"));
//            account.setFullname(object.getString("fullname"));
//            account.setAddress(object.getString("address"));
//            account.setAvatarBase64(object.getString("avatarBase64"));
//        } catch (Exception e) {
//            return Response
//                    .status(Response.Status.BAD_REQUEST)
//                    .entity(new AbstractMap.SimpleEntry<>("message", "Wrong JSON-requestbody!"))
//                    .build();
//        }
//
//        if (Company.getCompany().addAccount(account)) {
//            if (!account.getAvatarBase64().isEmpty()) {
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.saveUploadToAzure(base64);
//                account.setAvatarUploadId(uploadId);
//            }
//
//            return Response.ok(account).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
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
            messages.put("Error","Er klopt iets niet aan uw email!");
            return Response.status(Response.Status.CONFLICT).entity(messages).build();
        }
    }
}

//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//
//@POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccountFormData(@FormParam("username") String uN, @FormParam("fullname") String fN, @FormParam("address") String addr, @FormParam("avatarBase64") String aB64) {
//        Account account = new Account(uN, fN, addr, aB64);
//
//        if (Company.getCompany().addAccount(account)) {
//            if (!account.getAvatarBase64().isEmpty()) {
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.saveUploadToAzure(base64);
//                account.setAvatarUploadId(uploadId);
//            }
//
//            return Response.ok(account).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//}
