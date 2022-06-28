package archive.trawler.security;

import archive.trawler.security.dto.LoginRequest;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;

/**
 * Deze klasse authenticeert een gebruiker en wordt gebruikt bij inloggen.
 */
@PermitAll
@Slf4j
@Path("authentication")
public class AuthenticationResource {

    public static final Key key = MacProvider.generateKey();

    /**
     * Deze functie authenticeert een gebruiker en wordt gebruikt bij inloggen.
     *
     * @param logonRequest data incoming json logonRequest type
     * @return JWT:<br>
     * Een Signature van Header en Payload - Base64Url encoded +<br>
     * Hash van header, payload, en secret key<br>
     * Kan de server de authenticiteit mee controleren.<br>
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginRequest logonRequest) { /** Log-in */
        try {
            String role = MyUser.validateLogin(logonRequest.email, logonRequest.password);
            if (role == null) throw new IllegalArgumentException("No user found");
            System.out.println("login validated for role: "+role);

            String token = createToken(logonRequest.email, role);
            System.out.println("token created");
            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build(); //puur de 401 melding, geen verdere info
        }
    }

    /**
     * Bestaat uit drie door punten gescheiden onderdelen:<br>
     * Een Header met het token-type en signature algoritme - Base64Url encoded<br>
     * Een Payload met claims (statements over de gebruiker) - Base64Url encoded<br>
     * “Reserved” claims (bijvoorbeeld: issuer, expiration time, subject)<br>
     * “Private” claims (bijvoorbeeld: userrole)
     *
     * @param email de gebruikersnaam in context van deze app is dat een email
     * @param role  de rol van de gebruiker
     * @return JWT:<br>
     * Een Signature van Header en Payload - Base64Url encoded +<br>
     * Hash van header, payload, en secret key<br>
     * Kan de server de authenticiteit mee controleren.<br>
     * @throws JwtException JsonWebTokenException
     */
    public static String createToken(String email, String role) throws JwtException {
        System.out.println("creating token");
        int verloopTijdJWTToken = 30; // Token verloopt na 30 minuten
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, verloopTijdJWTToken);
        return Jwts.builder().setSubject(email).setExpiration(expiration.getTime()).claim("role", role).signWith(SignatureAlgorithm.HS512, key).compact();

    }
}
