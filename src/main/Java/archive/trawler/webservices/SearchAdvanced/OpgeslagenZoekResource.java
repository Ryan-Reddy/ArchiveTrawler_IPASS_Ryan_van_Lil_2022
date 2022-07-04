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

@Path("zoekertjes")
public class OpgeslagenZoekResource {
    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("haal-op")
    /* Haalt een Map met alle zoekertjes die bij deze gebruiker hoort. */
    public Response haalZoekertjesOp(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
            ArrayList<Zoekopdracht> alleZoekertjes = currentUser.getAlleZoekertjes();

            return Response.ok(alleZoekertjes).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Sla een nieuwe zoekopdracht op en voeg deze toe aan de lijst van deze gebruiker.
     * @param input De queryparameters die nodig zijn voor een nieuwe zoekopdracht.
     * @param sc SecurityContext, JTW.
     * @return Response of het gelukt is of niet.
     */
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
            if (input.archiveAmsterdam) {
                Archief archiveAmsterdam = Community.getArchiefByName("amsarchief");
            }
            theUser.addZoekertjeAanAlleZoekertjes(new Zoekopdracht(archiveChoices, input.keywords, theUser.getID()));
            return Response.ok().entity(theUser.getAlleZoekertjes()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Delete alle zoekertjes
     * @param sc securityContext
     * @return nieuwe lijst die bij de gebruiker hoort (leeg)
     */
    @DELETE
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete-all")
    public Response deleteAlleZoekertjes(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
            ArrayList<Zoekopdracht> empty_zoekertjes = new ArrayList<>();

            currentUser.setAlleZoekertjes(empty_zoekertjes);
            return Response.ok(currentUser.getAlleZoekertjes()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
