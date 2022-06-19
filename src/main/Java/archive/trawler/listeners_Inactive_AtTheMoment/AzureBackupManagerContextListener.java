package archive.trawler.listeners_Inactive_AtTheMoment;

//
//@WebListener
//public class AzureBackupManagerContextListener implements ServletContextListener {
//    public void contextInitialized(ServletContextEvent sce) {
//
//
////        try {
//            System.out.println("loading world from azure-storage");
////
////            PersistanceManager.loadCommunityFromAzure();
////        } catch (IOException e) {
////            System.out.println("catching IOException");
////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            System.out.println("catching ClassNotFoundException");
////            e.printStackTrace();
////        }
//
//        MyUser.registerUser("Coyote", "CasaSuCasa", "Wilde");
//                MyUser.registerUser("syd@barrett.com", "CrazyDiamond", "Floyd");
//                MyUser.registerUser("snoop@log.bomb", "pickItUpLikeItsCold", "Ryan");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        /* Overige code, om naar Azure te schrijven! */
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