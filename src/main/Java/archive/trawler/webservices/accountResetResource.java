package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.webservices.dto.ResetAccount;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.FileNotFoundException;

@Path("accountReset")
public class accountResetResource {
    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetWachtWoord(ResetAccount resetAccount) {
        try {
            SendEmail.sendMail(resetAccount.email, "Uw wachtwoord reset link.", "src/main/resources/emailHTMLTemplates/resetMail.html");
            return Response.ok(resetAccount).build(); // ok = 200;
        } catch (Exception e) {
            return Response.status(Response.Status.valueOf(e.toString())).build(); // NO_Content =
        }
    }
}


