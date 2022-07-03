package archive.trawler.webservices.SearchAdvanced;


import archive.trawler.model.User;
import archive.trawler.model.Zoekopdracht;
import archive.trawler.webservices.dto.ZoekQueryOpslaan;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Map;

@Path("opgeslagen-zoekertjes")
public class OpgeslagenZoekResource {
    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
//    @Path("")
    public Response haalZoekertjesOp(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
            Map<Integer, Object> alleZoekertjes = currentUser.getAlleZoekertjes();
            return Response.ok(alleZoekertjes).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response slaZoekertjeOp(@Context SecurityContext sc, ZoekQueryOpslaan input) {
        if(sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();

        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}

