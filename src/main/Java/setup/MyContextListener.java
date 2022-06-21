package setup;

import archive.trawler.model.User;
import archive.trawler.persistance.Community;
import archive.trawler.persistance.PersistanceManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import archive.trawler.security.MyUser ;


@WebListener
public class MyContextListener implements ServletContextListener {

    /** contextInitialized
     * Deze functie runt automatisch bij het opstarten van het programma/server.
     * Voert automatisch een data persistentie handeling uit.
     * */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("contextInitialized");
            User.setAllUsers(new ArrayList<>());
            MyUser.setAllMyUsers(new ArrayList<>());
            PersistanceManager.loadFromAzure();  // data inladen van azure container
        } catch (IOException e) {
            System.out.println("catching IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("catching ClassNotFoundException");
            e.printStackTrace();
        }
        /*new User("Coyote", "CasaSuCasa", "Wilde");
        new User( "CrazyDiamond","syd@barrett.com", "Floyd");
        new User( "pickItUpLikeItsCold", "snoop@log.bomb","Ryan");*/
    }

    /** contextDestroyed
     * Deze functie runt automatisch bij het afsluiten van het programma/server.
     * Voert automatisch een data persistentie handeling uit.
     * */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        /* Overige code, bijvoorbeeld om naar Azure te schrijven! */
        System.out.println("Context destroyed, saving everything to azure-storage");
        try {
            PersistanceManager.uploadToAzure();  // data ophalen van de azure container
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Dit deel ruimt op na afsluiten van scherm: */
        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
