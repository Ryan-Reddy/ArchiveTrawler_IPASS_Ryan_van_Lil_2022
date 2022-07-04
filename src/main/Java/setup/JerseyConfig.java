package setup;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

/**
 * Deze klasse regelt het instellen van de Jersey JAX-RS
 * Ook stelt deze de packages in waarin de REST resources zitten.
 * @Attribute CorsFilter zorgt ervoor dat er vanaf andere sites de rest aangesproken kan worden.
 * @Attribute RolesAllowedDynamicFeature Maakt het makkelijk te verifieren wat voor type gebruiker er in de JWT zit.
 * @Attribute JacksonFeature Dit regelt de vertaling naar JSON en terug voor de responses van de services.
 */
@ApplicationPath("restservices")
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {
        System.out.println("[ JerseyConfig ] starting...");
        packages(
                "archive.trawler.webservices",
                "archive.trawler.security"
        );
        register(CorsFilter.class);
        register(RolesAllowedDynamicFeature.class);
        register(JacksonFeature.class);
    }
}
