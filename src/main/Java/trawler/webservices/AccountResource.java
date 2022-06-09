package trawler.webservices;

import trawler.model.Community;
import trawler.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Path("users")
public class AccountResource {
    @GET
    @Path("getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppers() {
        Community com = Community.getCommunity();
        List<Object> totaalMessages = new ArrayList<>();

        List<User> allUsers = com.getUsersAsList();

        for (User p : allUsers) {

            LinkedHashMap<String, Object> interMessage = new LinkedHashMap<>();

            interMessage.put("UserID", p.getUserID());
            interMessage.put("Voornaam", p.getVoorNaam());
            interMessage.put("Achternaam", p.getAchterNaam());
            interMessage.put("Email", p.getEmailAdres());
            interMessage.put("numberOfLists", p.getRole());

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
}
