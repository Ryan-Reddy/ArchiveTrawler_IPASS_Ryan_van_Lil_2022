package trawler.webservices;

import trawler.model.Community;
import trawler.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account/{userId}")
public class AccountResource {

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
