package trawler.webservices;

import trawler.model.Community;
import trawler.model.User;

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
        Community com = Community.getCommunity();
        List<Object> totaalMessages = new ArrayList<>();

        List<User> allUsers = com.getUsersAsList();

        for (User p : allUsers) {

            LinkedHashMap<String, Object> interMessage = new LinkedHashMap<>();

            interMessage.put("UserID", p.getUserID());
            interMessage.put("Achternaam", p.getNaam());
            interMessage.put("Email", p.getEmailAdres());
            interMessage.put("role", p.getRole());
            interMessage.put("Hoeveelheid zoekopdrachten/zoekertjes", p.getZoekertjes().size());


            totaalMessages.add(interMessage);
        }
        return Response.ok(totaalMessages).build();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("userId") String accountId) {
        User user = Community.getCommunity().getAccount(accountId);

        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("shopper/addnew/name={name}&email={email}&wachtwoord={wachtwoord}")

    public Response createNewUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("wachtwoord") String wachtwoord) {

        if(Community.getCommunity().getUsersAsList().stream().anyMatch(x -> name.equals(x.getEmailAdres()))){
            return Response.status(Response.Status.CONFLICT).entity("Klant bestaat al !").build();
        }
        new User(email,name,wachtwoord,"user");
        Map<String, String> messages = new HashMap<>();
        messages.put("SUCCES", "klant bestond nog niet, is nu aangemaakt nog niet! Welkom, "+name);
        return Response.ok(messages).build();
    }

    @DELETE
    @Path("shopper/delete={name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(@PathParam("name")String name) {
        return User.removeShopper(name)
                ?Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
