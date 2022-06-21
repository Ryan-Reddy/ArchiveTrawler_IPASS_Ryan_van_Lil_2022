package archive.trawler.webservices;

import archive.trawler.model.Community;
import archive.trawler.security.MyUser;
import archive.trawler.model.User;
import archive.trawler.webservices.dto.NewAccount;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.ok;

// TODO fix the fact that these restresources will run on tomcat locally but not on heroku
@Path("users")
public class AccountResource {

    /**
     * function getAllUsers
     * This function returns all users into one neat JSON
     *
     * @return JSON
     */
    @GET
    @Path("getall")
    // TODO functie getAllUsers
    //  werkt niet meer sinds implementatie van de BLOB
    //  labels: RESOURCES
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<Object> totaalMessages = new ArrayList<>();
        LinkedHashMap<String, Object> interMessage = new LinkedHashMap<>();

        Community.getUserMap().values()
                .forEach(user -> {
                    interMessage.put("Naam", user.getNaam());
                    interMessage.put("Email", user.getEmail());
                    interMessage.put("password", user.getPassword());
                    interMessage.put("zoekertjes", user.getAlleZoekertjes());
                    interMessage.put("role", user.getRole());
                    totaalMessages.add(interMessage);
                });

        return ok(totaalMessages).build();
    }

    /**
     * functie checkAPIstatus
     * check dat de rest API werkt
     */
    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAPIstatus() {
        return Response.ok("works").build();
    }

    /**
     * The resource getAccount willreturn the user and its data.
     *
     * @param email = the email connected to the users account.
     * @return User or NOT_FOUND
     */
    @Path("finduser?email={email}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("email") String email) {
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
    @Path("find_user?email={email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(@PathParam("email") String email) {
        return Community.deleteMyUserAccount(email)
                ? Response.ok(String.format("the user %s has been deleted..", email)).build()        // give ok http response if it works
                : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addnew")
    public Response createNewUser(NewAccount info) {
        try {
            if (User.getUserByEmail(info.email) != null) {  // als er al een useraccount met dit email bestaat:
                return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
            }
            User newUser = new User(info.email, info.password, info.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + info.name);
        return ok(messages).build();
    }
//     /** The resource getAccount willreturn the user and its data.
//     * @param email = the email connected to the users account.
//     * @return User or NOT_FOUND */
//    @Path("finduser?email={email}")
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAccount(@PathParam("email") String email) {
//        User theUser = User.getUserByEmail(email);
//
//        if (theUser != null) {
//            return Response.ok(theUser).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    /** deleteUserAccount
//     * will delete the MyUser account associated with this email adres.
//     * User part of the account WILL NOT be deleted by this resource, but will not be accessable by the end user.
//     */
//    @DELETE
//    @Path("find_user?email={email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteUserAccount(@PathParam("email") String email) {
//        return MyUser.deleteMyUserAccount(email)
//                ? Response.ok(String.format("the user %s has been deleted..", email)).build()        // give ok http response if it works
//                : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not
//    }

//     /** The resource getAccount willreturn the user and its data.
//     * @param email = the email connected to the users account.
//     * @return User or NOT_FOUND */
//    @Path("finduser?email={email}")
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAccount(@PathParam("email") String email) {
//        User theUser = User.getUserByEmail(email);
//
//        if (theUser != null) {
//            return Response.ok(theUser).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//
//    /** deleteUserAccount
//     * will delete the MyUser account associated with this email adres.
//     * User part of the account WILL NOT be deleted by this resource, but will not be accessable by the end user.
//     */
//    @DELETE
//    @Path("find_user?email={email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteUserAccount(@PathParam("email") String email) {
//        return MyUser.deleteMyUserAccount(email)
//                ? Response.ok(String.format("the user %s has been deleted..", email)).build()        // give ok http response if it works
//                : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("addnew/name={name}&email={email}&wachtwoord={wachtwoord}")
//    public Response createNewUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("wachtwoord") String wachtwoord) {
//        try {
//            MyUser.registerUser(email, wachtwoord, name);
//        } catch (Exception e) {
//            return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
//        }
//        Map<String, String> messages = new HashMap<>();
//        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, "+name);
//        return ok(messages).build();
//    }
}
