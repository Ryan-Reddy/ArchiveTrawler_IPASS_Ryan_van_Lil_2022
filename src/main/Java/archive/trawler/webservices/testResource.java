package archive.trawler.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class testResource {

    /** functie checkAPIstatus
     * check dat de rest API werkt
     */
    @GET
    @Path("status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAPIstatus() {
        return Response.ok("works").build();
    }

}
