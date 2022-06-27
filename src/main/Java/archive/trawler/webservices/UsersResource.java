package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.persistance.UploadsManager;
import archive.trawler.security.AuthenticationResource;
import archive.trawler.security.EncodedBase64;
import archive.trawler.security.MyUser;
import archive.trawler.webservices.dto.DeleteAccountDTO;
import archive.trawler.webservices.dto.NewAccount;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

import static javax.ws.rs.core.Response.ok;


// TODO https://bowser-snek.herokuapp.com/restservices/snake/
//  REST works on heroku, why not here?

@Path("users")
public class UsersResource {

    /**
     * function getAllUsers
     * This function returns all users into one neat JSON
     *
     * @return JSON
     */
    @GET
    @Path("")
    @RolesAllowed("user")
    @Produces("application/json")
    public Response getAllUsers(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            Map<String, User> commune = Community.getUserMap();
            JsonArrayBuilder jab = Json.createArrayBuilder();

            JsonObjectBuilder job = Json.createObjectBuilder();
            commune.forEach((key, user) -> {

                job.add("email", user.getEmail());
                job.add("naam", user.getNaam());
                job.add("role", user.getRole());
                job.add("hoeveelheid zoekopdrachten", user.getAlleZoekertjes().size());
                jab.add(job);
            });
            return ok(jab.build()).build();
        }
        return ok("error", "something sadly went wrong, contact the pope!").build();
    }

    /**
     * The resource getAccount willreturn the user and its data.
     *
     * @param email = the email connected to the users account.
     * @return User or NOT_FOUND
     */
    @GET
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response getAccount(String email, @Context SecurityContext sc) {
        User theUser = Community.getUserByEmail(email);
        if (theUser != null) {
            return Response.ok(theUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * deleteUserAccount
     * will delete the MyUser account associated with this email adres.
     * User part of the account WILL NOT be deleted by this resource, but will not be accessable by the end user.
     */
    @DELETE
    @RolesAllowed("user")
    @Path("deleteaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(@Context SecurityContext sc) {
        String email = "";
        if (sc.getUserPrincipal() instanceof User) {
            email = ((User) sc.getUserPrincipal()).getEmail();  // haalt email op uit de JWT
        }
            return Community.deleteMyUserAccount(email) ? Response.ok(String.format("Your account has been deleted.. %s", email)).build()        // give ok http response if it works
                    : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not


    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("")
//    public Response createNewUser(NewAccount info) {
//        try {
//            if (User.getUserByEmail(info.email) != null) {  // als er al een useraccount met dit email bestaat:
//                return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
//            }
//            User newUser = new User(info.name, info.email, info.password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Map<String, String> messages = new HashMap<>();
//        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + info.name);
//        return ok(messages).build();
//    }
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response createAccountFullJackson(User account) {
    if (Community.getCommunity().addUserToMap(account)) {
        if (!account.getAvatarBase64().isEmpty()) {
            EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
            String uploadId = UploadsManager.uploadToAzure(base64); // upload Id is de unieke blob voor deze upload
            account.setAvatarUploadId(uploadId);
        }

        return Response.ok(account).build();
    } else {
        return Response.status(Response.Status.CONFLICT).build();
    }
}



    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response patchUser(NewAccount info) {
        //TODO create patchUser pipeline in html but also here
        try {
            if (User.getUserByEmail(info.email) != null) {  // als er al een useraccount met dit email bestaat:
                return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
            }
            User newUser = new User(info.naam, info.email, info.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + info.naam);
        return ok(messages).build();
    }
}
