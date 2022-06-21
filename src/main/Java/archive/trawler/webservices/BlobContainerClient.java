//package archive.trawler.webservices;
//
//import com.azure.storage.blob.BlobContainerAsyncClient;
//
//public final class BlobContainerClient {
//
//    public static final String ROOT_CONTAINER_NAME = BlobContainerAsyncClient.ROOT_CONTAINER_NAME;
//
//
//    public static final String STATIC_WEBSITE_CONTAINER_NAME = BlobContainerAsyncClient.STATIC_WEBSITE_CONTAINER_NAME;
//
//    public static final String LOG_CONTAINER_NAME = BlobContainerAsyncClient.LOG_CONTAINER_NAME;
//    private final BlobContainerAsyncClient client;
//
//
//    BlobContainerClient(BlobContainerAsyncClient client) {
//        this.client = client;
//    }
//
//    //    @ServiceMethod(returns = ReturnType.SINGLE)
////    public void create() {
////        try {
////            client.createWithResponse();
////        } catch (Exception e) {
////            System.out.println(e);;
////        }
////    }
//    public void create() {
//        System.out.println("deze methode moet nog geschreven worden");
//    }
//
//    public void delete() {
//        System.out.println("deze methode moet nog geschreven worden");
//    }
//
//    public boolean exists() {
//        System.out.println("deze methode moet nog geschreven worden");
//        return false;
//    }
//
//    public String getAccountName() {
//        System.out.println("deze methode moet nog geschreven worden");
//        return "this still needs to be programmed";
//    }
//    //    public BlobClient getBlobClient(String blobName) {
////        return new BlobClient();
//////        return client.getBlobClient(blobName);
////    };
////
////
////    public List<BlobItem> listBlobs() {};
//
//}
