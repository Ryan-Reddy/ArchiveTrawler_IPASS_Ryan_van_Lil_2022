package archive.trawler.webservices;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.webservices.dto.PasswordReset;
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
        System.out.println("account-resetWachtWoord() gestart");
        try {
            User thisUser = Community.getUserByEmail(resetAccount.email);
            String email = resetAccount.email;
            String token = createToken(email, thisUser.getRole());
            String subject = "Hier "+ thisUser.getNaam() +", uw wachtwoord reset link.";
            SendEmail.sendMailWithToken(email, subject , token);
            return Response.ok(resetAccount).build(); // ok = 200;
        } catch (Exception e) {
            return Response.status(Response.Status.valueOf(e.toString())).build(); // NO_Content =
        }
    }

    @PATCH
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("wijzig-wachtwoord")
    public Response patchUser(PasswordReset info, @Context SecurityContext sc) {
            if (sc.getUserPrincipal() instanceof User) {
                try {
                    User currentUser = (User) sc.getUserPrincipal();
                    if (info.password.equals(info.password2)) {
                        System.out.println("ww is reset !");
                        currentUser.setPassword(info.password);
                        return Response.ok().build();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return Response.status(Response.Status.UNAUTHORIZED).entity("2 Er ging iets mis, bent u ingelogd?").build();
    }
}

