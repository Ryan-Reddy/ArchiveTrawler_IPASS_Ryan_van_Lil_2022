package setup;

import archive.trawler.model.Archief;
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

import static archive.trawler.persistance.PersistanceManager.communityContainerbackup;
import static archive.trawler.persistance.PersistanceManager.usersBlobNamebackup;

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
            PersistanceManager.loadFromAzure();  // data inladen van azure container
            PersistanceManager.uploadToAzure(usersBlobNamebackup,communityContainerbackup);     ////////  /////  /// /* BACKUP */ ///  /////  ////////

        } catch (IOException e) {
            System.out.println("catching IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("catching ClassNotFoundException");
            e.printStackTrace();
        }
        // DUMMYDATA:
//        new User("Coyote", "test@mail.com", "password");
//        new User("TickTock", "aldous@harding.com", "Wilde");
//        new User( "CrazyDiamond","syd@barrett.com", "Floyd");
//        new User( "pickItUpLikeItsCold", "snoop@log.bomb","Ryan");
        new Archief("test","");
        System.out.println("~~~~~~~~~~ Community.getCommunity().toString()" + Community.getCommunity().toString());
        System.out.println("~~~~~~~~~~ Community.getUserMap().toString()" + Community.getUserMap().toString());
        System.out.println("~~~~~~~~~~ Community.getUserMap().toString()" + Community.getUserMap().toString());

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
            PersistanceManager.uploadToAzure("communityblob",PersistanceManager.communityContainer);  // data ophalen van de azure container

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Dit deel ruimt op na afsluiten van scherm: */
        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
