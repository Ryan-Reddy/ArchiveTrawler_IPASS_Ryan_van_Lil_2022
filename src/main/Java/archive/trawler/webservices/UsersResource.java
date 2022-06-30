package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.security.dto.EmailToSearchSingleAccount;
import archive.trawler.security.dto.LoginRequest;
import archive.trawler.webservices.dto.NewAccount;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
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
     * The resource getAccount willreturn the user and its data.
     *
     * @param info = the email connected to the users account, DTO class met alleen een email als inhoud.
     * @return User or NOT_FOUND
     */
    @POST
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAccount")
    public Response getAccount(EmailToSearchSingleAccount info, @Context SecurityContext sc) {
        User theUser;

//        if (info != null) {
//            theUser = Community.getCommunity().getUserByEmail(info.email);
//            return Response.ok(theUser).build(); // ok = 200;
//        }
        if (sc.getUserPrincipal() instanceof User) {
            theUser = (User) sc.getUserPrincipal();
            System.out.println(theUser.getEmail());
            System.out.println(theUser.getNaam());
            System.out.println(theUser.getPassword());
            return Response.ok(theUser).build(); // ok = 200;
        }
        return Response.status(Response.Status.NO_CONTENT).build(); // NO_Content =
    }

    /**
     * function getAllUsers
     * This function returns all users into one neat JSON
     *
     * @return JSON
     */
//    @PermitAll
    @GET
    @Path("getall")
//    @RolesAllowed("user")
    @Produces("application/json")
    public Response getAllUsers(@Context SecurityContext sc) {
//        try {
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
                System.out.println("Jab added to Job");
            });
            return Response.ok(jab.build()).build();
        }
//        }
//        catch (Exception e) {
        return ok("error", "something sadly went wrong, contact the pope!").build();
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
    public Response deleteUserAccount(LoginRequest logonRequest, @Context SecurityContext sc) {
        System.out.println("deleteUserAccount started");
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
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccountFullJackson(User account) { // check wether to use DTO or not
//    if (Community.getCommunity().addUserToMap(account)) {
////            if (!account.getAvatarBase64().isEmpty()) {
////                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
////                String uploadId = UploadsManager.uploadToAzure(base64); // upload Id is de unieke blob voor deze upload
////                account.setAvatarUploadId(uploadId);
////            }
//
//            return Response.ok(account).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }


    @PATCH
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("wijzigUser")
    public Response patchUser(NewAccount info, @Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
            try {
                currentUser.setEmail(info.email);
                currentUser.setNaam(info.naam);
                return Response.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.status(Response.Status.CONFLICT).entity("2 Er ging iets mis, bent u ingelogd?").build();
    }
}
