package setup;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {
        packages(
                "archive.trawler.webservices",
                "archive.trawler.security"
        );
        register(RolesAllowedDynamicFeature.class);
        register(JacksonFeature.class);
//        register(CorsFilter.class);
    }
}
