package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.security.MyUser;
import archive.trawler.webservices.dto.DeleteAccountDTO;
import archive.trawler.webservices.dto.NewAccount;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@Context SecurityContext sc) {
        if(sc.getUserPrincipal() instanceof  User) {
            User current = (User) sc.getUserPrincipal();

            JsonArrayBuilder jab = Json.createArrayBuilder();
            Community.getUserMap().forEach((key, user) -> {
                        JsonObjectBuilder job = Json.createObjectBuilder();
                        job.add("Naam", user.getNaam());
                        job.add("Email", user.getEmail());
                        job.add("password", user.getPassword());
                        job.add("zoekertjes", (JsonValue) user.getAlleZoekertjes());
                        job.add("role", user.getRole());
                        jab.add(job);
                    });

            System.out.println(jab);
            return ok(jab).build();
        } return ok("error", "something sadly went wrong, contact the pope!").build();
    }

    /** The resource getAccount willreturn the user and its data.
     *
     * @param email = the email connected to the users account.
     * @return User or NOT_FOUND
     */
    @Path("{email}")
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
    @Path("deleteaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(DeleteAccountDTO email) {
        return Community.deleteMyUserAccount(email.email)
                ? Response.ok(String.format("the user %s has been deleted..", email)).build()        // give ok http response if it works
                : Response.status(Response.Status.NOT_FOUND).build();   // give not found response if not
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response createNewUser(NewAccount info) {
        try {
            if (User.getUserByEmail(info.email) != null) {  // als er al een useraccount met dit email bestaat:
                return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
            }
            User newUser = new User(info.name, info.email, info.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + info.name);
        return ok(messages).build();
    }
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{email}")
    public Response patchUser(NewAccount info) {
        //TODO create patchUser pipeline in html but also here
        try {
            if (User.getUserByEmail(info.email) != null) {  // als er al een useraccount met dit email bestaat:
                return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
            }
            User newUser = new User(info.name, info.email, info.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, " + info.name);
        return ok(messages).build();
    }
}
