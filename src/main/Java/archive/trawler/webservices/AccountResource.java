package archive.trawler.webservices;

import archive.trawler.Security.MyUser;
import archive.trawler.model.Community;
import archive.trawler.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


@Path("users")
public class AccountResource {

    @GET
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllShoppers() {

        List<Object> totaalMessages = new ArrayList<>();
        List<User> allUsers = User.getAllUsers();

        for (User p : allUsers) {

            LinkedHashMap<String, Object> interMessage = new LinkedHashMap<>();
            interMessage.put("Naam", p.getNaam());
            interMessage.put("Email", p.getEmailAdres());
            interMessage.put("role", p.getRole());

            totaalMessages.add(interMessage);
        }
        return Response.ok(totaalMessages).build();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("userId") String accountId) {
        User user = Community.getCommunity().getUserByName(accountId);

        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addnew?name={name}&email={email}&wachtwoord={wachtwoord}")

    public Response createNewUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("wachtwoord") String wachtwoord) {
try {
        MyUser.registerUser(email, wachtwoord, name);
        } catch(Exception e) {
            return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, "+name);
        return Response.ok(messages).build();
    }

//    @DELETE
//    @Path("shopper/delete={name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteUserAccount(@PathParam("name")String name) {
//        return User.removeShopper(name)
//                ?Response.ok().build()
//                : Response.status(Response.Status.NOT_FOUND).build();
//    }
}
