package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.webservices.dto.ResetAccount;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import static archive.trawler.security.AuthenticationResource.createToken;

@Path("account-reset")
public class accountResetResource {
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetWachtWoord(ResetAccount resetAccount) {
        try {
            User thisUser = Community.getUserByEmail(resetAccount.email);
            String email = resetAccount.email;
            String token = createToken(email, thisUser.getRole());
            SendEmail.sendMailWithToken(email, "Uw wachtwoord reset link.",token);
            return Response.ok(resetAccount).build(); // ok = 200;
        } catch (Exception e) {
            return Response.status(Response.Status.valueOf(e.toString())).build(); // NO_Content =
        }
    }


    // TODO schrijf functiedie een token genereert en toevoegt aan de email =>  <script>  in de html mail zelf !
    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("password-reset")
    public Response passwordLessLogin(@Context SecurityContext sc) {
        if (sc.getUserPrincipal() instanceof User) {
            User currentUser = (User) sc.getUserPrincipal();
        } // TODO schrijf wellicht is deze functie niet nodig, als de fetch wijzigWW controleert of de gebruiker JWT auth is.
        // sla in gebruik token om te verifieren dat dit de gebruiker is

        // send gebruiker naar ww wijzig pagina
        return Response.ok("er ging iets mis met uw login, vraag nogmaals een link op").build();
    }
}


