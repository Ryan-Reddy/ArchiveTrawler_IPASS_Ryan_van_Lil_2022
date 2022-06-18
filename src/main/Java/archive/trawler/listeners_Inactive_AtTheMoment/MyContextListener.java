package archive.trawler.listeners_Inactive_AtTheMoment;





import archive.trawler.Security.MyUser;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;

@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyUser.registerUser("Coyote", "CasaSuCasa", "Wilde");
        MyUser.registerUser("syd@barrett.com", "CrazyDiamond", "Floyd");
        MyUser.registerUser("snoop@log.bomb", "pickItUpLikeItsCold", "Ryan");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        /* Overige code, bijvoorbeeld om naar Azure te schrijven! */

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
