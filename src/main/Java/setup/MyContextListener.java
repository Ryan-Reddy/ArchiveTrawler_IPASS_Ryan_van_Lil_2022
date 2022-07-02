package setup;

import archive.trawler.model.Archief;
import archive.trawler.model.User;
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

        } catch (IOException e) {
            System.out.println("catching IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("catching ClassNotFoundException");
            e.printStackTrace();
        }
            // DUMMYDATA:
            new User("Coyote", "test@mail.com", "password");
        new User("TickTock", "aldous@harding.com", "Wilde");
        new User( "CrazyDiamond","syd@barrett.com", "Floyd");
        new Archief("amsarchief","https://webservices.picturae.com/genealogy/person?apiKey=eb37e65a-eb47-11e9-b95c-60f81db16c0e&page=1&q={naam}&rows=1000&sort=score%20desc");
        new Archief("openarchief","https://api.openarch.nl/1.0/records/search.json?name={naam}&lang=nl&number_show=100&sort=1&start=0&archive");
//        new User( "pickItUpLikeItsCold", "snoop@log.bomb","Ryan");
//        new Archief("test","");
//        new Zoekopdracht(Arrays.asList(Community.getArchiefByName("test")),"naam blabla werkman", Community.getUserByEmail("test@mail.com"));
//
//        System.out.println("~~~~~~~~~~ Community.getCommunity().toString()" + Community.getCommunity().toString());
//        System.out.println("~~~~~~~~~~ Community.getUserMap().toString()" + Community.getUserMap().toString());
//        System.out.println("~~~~~~~~~~ Community.getUserMap().toString()" + Community.getUserMap().toString());
//        System.out.println("~~~~~~~~~~ Community.getArchiefMap().toString()" + Community.getArchiefMap().toString());
//        System.out.println("~~~~~~~~~~ Community.getArchiefMap().toString()" + Community.getZoekOpdrachtMap().toString());

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
