package listeners_Inactive_AtTheMoment;

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
//        User u1 = new User("everette65@yahoo.com", "okeefe.bennie derrick87", "n67RG/rX~&x6nv", "user");
//        User u4 = new User("marty07@yahoo.com", "chase61 scrist", "NHw!.@mSD?C9", "user");
//        User u5 = new User("abigail.vonrueden@parisian.info","eziemann", "22I^5ollR", "user");
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