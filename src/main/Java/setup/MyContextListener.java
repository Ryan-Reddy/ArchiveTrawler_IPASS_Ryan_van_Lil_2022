package setup;

import archive.trawler.model.User;
import archive.trawler.persistance.PersistanceManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import archive.trawler.security.MyUser ;

import static archive.trawler.model.User.registerUser;

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

        registerUser(new User("Coyote", "CasaSuCasa", "Wilde"));
        registerUser(new User("syd@barrett.com", "CrazyDiamond", "Floyd"));
        registerUser(new User("snoop@log.bomb", "pickItUpLikeItsCold", "Ryan"));
        System.out.println("made the users.................");
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
