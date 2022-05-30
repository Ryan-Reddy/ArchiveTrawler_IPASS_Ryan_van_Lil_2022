package main.archivetrawler.listeners;

//import reactor.core.scheduler.Schedulers;
//import reactor.netty.http.HttpResources;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.time.Duration;
//
//@WebListener
//public class MyContextListener implements ServletContextListener {
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        /* Overige code, bijvoorbeeld om naar Azure te schrijven! */
//
//        Schedulers.shutdownNow();
//        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
//    }
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {}
//}
