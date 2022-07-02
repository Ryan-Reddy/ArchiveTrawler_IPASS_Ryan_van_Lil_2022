package archive.trawler.webservices.SearchAdvanced;


import archive.trawler.webservices.dto.SearchAdvancedInput;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("search-advanced-service")
public class SearchAdvancedResource {
    /**
     * advanced search, handled de zoekquery vanuit zoeken-advanced formulier op de website.
     *
     * @param searchQuery searchquery van het type JSON > vorm: DTO SearchAdvancedInput
     */
    @POST
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response advancedSearchProcess(SearchAdvancedInput searchQuery, @Context SecurityContext sc) {
        // TODO schrijf handle de input van searchQuery
        //  create fetchURI per archief in searchQuery > archiveAmsterdam & archiveNoordHolland
        //  labels: advancedSearchProcess
        return Response.ok().build();
    }
}
