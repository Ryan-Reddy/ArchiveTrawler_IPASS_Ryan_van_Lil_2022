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

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // https://portal.azure.com/?Microsoft_Azure_Education_correlationId=6b40be811cf2439b9e3500daa31e6825&Microsoft_Azure_Education_newA4E=true&Microsoft_Azure_Education_asoSubGuid=b1cc8d8a-8cf1-470c-9767-db2e923ab8ab#@hogeschoolutrecht.onmicrosoft.com/resource/subscriptions/b1cc8d8a-8cf1-470c-9767-db2e923ab8ab/resourceGroups/IPASS_ArchiveTrawler/providers/Microsoft.Storage/storageAccounts/ryanreddyipass/overview
        try {
            System.out.println("contextInitialized");
            User.setAllUsers(new ArrayList<>());
            MyUser.setAllMyUsers(new ArrayList<>());

            PersistanceManager.loadFromAzure();
        } catch (IOException e) {
            System.out.println("catching IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("catching ClassNotFoundException");
            e.printStackTrace();
        }
        // TODO
        //  Code now runs nicely, making a new user works, shows up in the getAll requests
        //  Main problem is that when running dummy data they seem to multiply and having seperate ones doesnt anymore ->>

        System.out.println("making dummy users.............|");
        Community.addUserToMap(new User("Coyote", "CasaSuCasa", "Wilde"));
        Community.addUserToMap(new User( "CrazyDiamond","syd@barrett.com", "Floyd"));
        Community.addUserToMap(new User( "pickItUpLikeItsCold", "snoop@log.bomb","Ryan"));
        System.out.println("made the users.................|");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        // https://portal.azure.com/?Microsoft_Azure_Education_correlationId=6b40be811cf2439b9e3500daa31e6825&Microsoft_Azure_Education_newA4E=true&Microsoft_Azure_Education_asoSubGuid=b1cc8d8a-8cf1-470c-9767-db2e923ab8ab#@hogeschoolutrecht.onmicrosoft.com/resource/subscriptions/b1cc8d8a-8cf1-470c-9767-db2e923ab8ab/resourceGroups/IPASS_ArchiveTrawler/providers/Microsoft.Storage/storageAccounts/ryanreddyipass/overview

        /* Overige code, bijvoorbeeld om naar Azure te schrijven! */
        System.out.println("Context destroyed, saving everything to azure-storage");
        try {
            PersistanceManager.saveToAzure();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
