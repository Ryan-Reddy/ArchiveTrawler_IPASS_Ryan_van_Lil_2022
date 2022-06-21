package archive.trawler.persistance;


import archive.trawler.model.User;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;
import java.util.Map;

public class PersistanceManager {
    //    private final static String ENDPOINT = "https://ryanreddyipass.blob.core.windows.net//";
//    private final static String SASTOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-10T23:00:06Z&st=2022-06-10T15:00:06Z&spr=https&sig=xEw9S%2FLZCky2l1L7kVSQrfQ%2FsD3HpYjyAokp0gVT%2FLs%3D";
//    private final static String CONTAINER = "communitycontainer";
//
//    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
//            .endpoint(ENDPOINT)
//            .sasToken(SASTOKEN)
//            .containerName(CONTAINER)
//            .buildClient();
    private static final BlobContainerClient usersContainer = new BlobContainerClientBuilder().endpoint("https://ryanreddyipass.blob.core.windows.net/").sasToken("?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-20T03:25:48Z&st=2022-06-19T19:25:48Z&spr=https&sig=EbqeDhkZAUbJn5FhVooIwqTNXTBWNJKRxsF7np1wnTY%3D").containerName("userscontainer").buildClient();

    private static final BlobContainerClient myusersContainer = new BlobContainerClientBuilder().endpoint("https://ryanreddyipass.blob.core.windows.net/").sasToken("?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-20T03:25:48Z&st=2022-06-19T19:25:48Z&spr=https&sig=EbqeDhkZAUbJn5FhVooIwqTNXTBWNJKRxsF7np1wnTY%3D").containerName("myuserscontainer").buildClient();

    private static final String myUsersBlobName = "myUsersBlobby";
    private static final String usersBlobName = "usersBlobby";

    //=========================================================================================================
//
//    public static void loadMyUsersFromAzure() throws IOException, ClassNotFoundException {
////
////        // creeert een blobcontainer als deze nog niet bestond
////        if (!usersContainer.exists()) {
////            usersContainer.create(); TODO reinsert
////        }
//        if (myusersContainer.exists()) {
//            System.out.println("loading MyUsers from azure");
//            /* Maak een blobclient aan */
//            BlobClient blob = myusersContainer.getBlobClient("myUsers12");
//
//        }
//    }
//    public static void loadUsersFromAzure() throws IOException, ClassNotFoundException {
////
////        // creeert een blobcontainer als deze nog niet bestond
////        if (!usersContainer.exists()) {
////            usersContainer.create(); TODO reinsert
////        }
//        if (usersContainer.exists()) {
//            System.out.println("loading Users from azure");
//            /* Maak een blobclient aan */
//            BlobClient blob = usersContainer.getBlobClient("myUsers12");
//            if (blob.exists()) {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                blob.download(baos);
//
//                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//
//                ObjectInputStream ois = new ObjectInputStream(bais);
//
//                List<User> loadedMyUsers = (List<User>) ois.readObject();
//                User.setUsers(loadedMyUsers);
//
//                baos.close();
//                bais.close();
//                ois.close();
//            }
//        }
//    }
    public static void loadFromAzure() throws IOException, ClassNotFoundException {
        if (!usersContainer.exists()) {
            usersContainer.create();
        }// creeert een blobcontainer als deze nog niet bestond

        if (usersContainer.exists()) {
            System.out.println("~~~ loading all Users-data from azure ~~~");
            BlobClient usersBlob = usersContainer.getBlobClient(usersBlobName); // Maak een blobclient aan voor Users
            if (Boolean.TRUE.equals(usersBlob.exists())) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                usersBlob.download(baos); // download van de blob, overschrijven ja.
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Community.setUserMap((Map<String, User>) ois.readObject());
                System.out.println("~~~ Succesfully loaded all Users-data from azure ~~~");
                baos.close();
                bais.close();
                ois.close();
            }
        }
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void saveToAzure() throws IOException {
        if (!myusersContainer.exists()) {
            myusersContainer.create();
        }// creeert een blobcontainer als deze nog niet bestond

        if (myusersContainer.exists()) {
            BlobClient myUsersBlob = myusersContainer.getBlobClient(myUsersBlobName);
            if (Boolean.TRUE.equals(myUsersBlob.exists())) {
                System.out.println("~~~ loading all Users-data from azure ~~~");

                if (!usersContainer.exists()) {usersContainer.create();}// creeert een blobcontainer als deze nog niet bestond

                if (usersContainer.exists()) {
                    BlobClient usersBlob = usersContainer.getBlobClient(usersBlobName); // Maak een blobclient aan voor Users
                    if (usersBlob.exists()) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(baos);

                        oos.writeObject(User.getAllUsers());

                        byte[] bytez = baos.toByteArray();   // binair maken
                        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
                        usersBlob.upload(bais, bytez.length, true);  // upload naar de blob, overschrijven ja.
                        baos.close();
                        oos.close();
                        bais.close(); // allestreams closen
                    }
                }
            }
        }
    }
}
