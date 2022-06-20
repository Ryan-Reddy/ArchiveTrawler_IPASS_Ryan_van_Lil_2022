

// Resources will be tested in POSTMAN

//package Resources;
//
//import archive.trawler.security.MyUser;
//import org.jboss.resteasy.*;
//import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
//import org.junit.jupiter.api.*;
//
//import static archive.trawler.model.User.getAllUsers;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class AccountResource {
//    TJWSEmbeddedJaxrsServer server = new TJWSEmbeddedJaxrsServer();
//    @BeforeAll
//    void setup() {
//        server.setPort(12345); // set de mockserver at port 12345 vaak ongebruikt ;)
////        server.getDeployment().getResources().add(getAllUsers());
//        // alternative
//        server.getDeployment().getProviderClasses().add("archive.trawler.webservices"); // laad de class met services in
//        server.start(); // start fake server om te testen
//    }
//
//    @Test
//    void testGetAllUsers() {
//        new ResteasyClientBuilder().build().target("http://localhost:12345/myresource").request().get();
//
//    }
//}