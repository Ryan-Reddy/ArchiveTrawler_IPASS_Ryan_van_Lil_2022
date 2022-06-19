package archive.trawler.persistance;


import archive.trawler.model.Community;
import archive.trawler.model.User;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;
import java.util.List;

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

    private static BlobContainerClient usersContainer = new BlobContainerClientBuilder()
            .endpoint("https://ryanreddyipass.blob.core.windows.net/")
            .sasToken("sp=racwdli&st=2022-06-19T16:01:49Z&se=2032-06-20T00:01:49Z&spr=https&sv=2021-06-08&sr=c&sig=CbL68sVcm6GMYXKpRRiygECE3HY5q0YDeW83MkiJtrs%3D")
            .containerName("userscontainer")
            .buildClient();

    private static BlobContainerClient myusersContainer = new BlobContainerClientBuilder()
            .endpoint("https://ryanreddyipass.blob.core.windows.net/")
            .sasToken("sp=racwdli&st=2022-06-19T16:07:15Z&se=2032-06-20T00:07:15Z&spr=https&sv=2021-06-08&sr=c&sig=aOESXwnMC8jGF8oB1pu4J5llyfpusCkmQtf0Nz4%2BCys%3D")
            .containerName("myuserscontainer")
            .buildClient();
    //=========================================================================================================


    public static void loadCommunityFromAzure() throws IOException, ClassNotFoundException {

        // creeert een blobcontainer als deze nog niet bestond
        if (!usersContainer.exists()) {
            usersContainer.create();
        }


        if (usersContainer.exists()) {

            /* Maak een blobclient aan */
            BlobClient blob = usersContainer.getBlobClient("loaded_user");
            if (blob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                ObjectInputStream ois = new ObjectInputStream(bais);

                Community loadedCommunity = (Community) ois.readObject();
                Community.setCommunity(loadedCommunity);

                baos.close();
                bais.close();
                ois.close();
            }
        }
    }


    public static void saveCommunityToAzure() throws IOException {
        // creeert een blobcontainer als deze nog niet bestond
        if (!usersContainer.exists()) {
            usersContainer.create();
        }


        BlobClient blob = usersContainer.getBlobClient("users");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        List UserstoSave = User.getAllUsers();
//        Community communityToSave = Community.getCommunity();
        oos.writeObject(UserstoSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        baos.close();
        oos.close();
        bais.close();
    }
}
