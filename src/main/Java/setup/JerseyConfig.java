package setup;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {
        packages("archive.trawler.webservices", "Security");
    }

}
