package archive.trawler.webservices.SearchAdvanced;


import archive.trawler.model.Archief;
import archive.trawler.model.User;
import archive.trawler.model.Zoekopdracht;
import archive.trawler.persistance.Community;
import archive.trawler.webservices.dto.ZoekQueryOpslaan;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Map;

@Path("zoekertjes")
public class OpgeslagenZoekResource {
    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("haal-op")
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sla-op")
    public Response slaZoekertjeOp(ZoekQueryOpslaan input, @Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
        User theUser = (User) sc.getUserPrincipal();
            System.out.println(input.archiveAmsterdam);
            ArrayList<Archief> archiveChoices = new ArrayList<Archief>();
            if(input.archiveAmsterdam = true) {
                Archief archiveAmsterdam = Community.getArchiefByName("amsarchief");
            }
        theUser.addZoekertjeAanAlleZoekertjes(new Zoekopdracht(archiveChoices,input.keywords,theUser));


        return Response.ok().entity(theUser.getAlleZoekertjes()).build();

            }
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.UNAUTHORIZED).build();
//    }
    }

}