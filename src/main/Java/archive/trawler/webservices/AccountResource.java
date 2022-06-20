package archive.trawler.webservices;

import archive.trawler.security.MyUser;
import archive.trawler.model.User;
import archive.trawler.webservices.dto.NewAccount;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.ok;


@Path("users")
public class AccountResource {

    /** function getAllUsers
     * This function returns all users into one neat JSON
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
        List<User> allUsers = User.getAllUsers();


        for (User p : allUsers) {
            LinkedHashMap<String, Object> interMessage = new LinkedHashMap<>();
            interMessage.put("Naam", p.getNaam());
            interMessage.put("Email", p.getEmailAdres());
            interMessage.put("role", p.getRole());
            interMessage.put("allUsers",allUsers.toString());
            totaalMessages.add(interMessage);
        }
        return ok(totaalMessages).build();
    }

     /** The resource getAccount willreturn the user and its data.
     * @param email = the email connected to the users account.
     * @return User or NOT_FOUND */
    @Path("find_user/{email}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("email") String email) {
        User theUser = User.getUserByEmail(email);

        if (theUser != null) {
            return Response.ok(theUser).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /** deleteUserAccount
     * will delete the MyUser account associated with this email adres.
     * User part of the account WILL NOT be deleted by this resource, but will not be accessable by the end user.
     */
    @DELETE
    @Path("find_user/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(@PathParam("email") String email) {
        return MyUser.deleteMyUserAccount(email)
                ? Response.ok(String.format("the user %s has been deleted..", email)).build()        // give ok http response if it works
                : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addnew")
    public Response createNewUser(NewAccount info) {
        try {
            MyUser.registerUser(info.email, info.password, info.name);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, "+info.name);
        return ok(messages).build();
    }
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
