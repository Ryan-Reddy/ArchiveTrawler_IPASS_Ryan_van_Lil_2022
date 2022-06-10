//package listeners;
//
//import persistance.PersistanceManager;
//import reactor.netty.http.HttpResources;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.IOException;
//import java.time.Duration;
//
//@WebListener
//public class AzureBackupManagerContextListener implements ServletContextListener {
//    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("loading world from azure-storage");
//
//
//        try {
//            PersistanceManager.loadCommunityFromAzure();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        /* Overige code, bijvoorbeeld om naar Azure te schrijven! */
//        System.out.println("saving world to azure-storage");
//        try {
//            PersistanceManager.saveCommunityFromAzure();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        Schedulers.shutdownNow();
//        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
//    }
//}