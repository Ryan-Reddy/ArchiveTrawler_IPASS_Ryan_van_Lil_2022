package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.security.dto.EmailToSearchSingleAccount;
import archive.trawler.security.dto.LoginRequest;
import archive.trawler.webservices.dto.NewAccount;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Map;


//  TODO REST works on heroku, why not here?

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
//        User theUser;

//        if (info != null) {
//            theUser = Community.getCommunity().getUserByEmail(info.email);
//            return Response.ok(theUser).build(); // ok = 200;
//        }
        if (sc.getUserPrincipal() instanceof User) {
            User theUser = (User) sc.getUserPrincipal();
            System.out.println(theUser.getEmail());
            System.out.println(theUser.getNaam());
            System.out.println(theUser.getPassword());
            return Response.ok(theUser).build(); // ok = 200;
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("something went wrong, make sure you are logged in!").build();
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
        return Response.status(Response.Status.UNAUTHORIZED).entity("something went wrong, make sure you are logged in!").build();
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
                : Response.status(Response.Status.UNAUTHORIZED).entity("something went wrong, make sure you are logged in!").build();


    }

    /**
     * Rest service om de informatie van de gebruiker te wijzigen
     * @param info De informatie uit het wijzigingen formulier
     * @param sc De security context, oftewel JWT.
     * @return Een Jackson response met daarin of het gelukt is of niet.
     */
    @PATCH
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("wijzigUser")
    public Response patchUser(NewAccount info, @Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
            try {
                currentUser.setEmail(info.email); // geeft info mee aan de DTO.
                currentUser.setNaam(info.naam);
                return Response.ok("Het wijzigen is gelukt.").build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("something went wrong, make sure you are logged in!").build();
    }
}