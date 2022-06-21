package archive.trawler.persistance;


import archive.trawler.model.User;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;
import java.util.Map;

public class PersistanceManager {
    private static final String ENDPOINT = "https://ryanreddyipass.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-22T01:55:53Z&st=2022-06-21T17:55:53Z&spr=https&sig=o6Ovg7nmpYR%2Beyll00G399spw9fcR4XdH1y%2FVTs9W34%3D";
    private static final String CONTAINER = "communitycontainer";
    //    private static final String usersBlobName = "communityblob";
    private static final BlobContainerClient communityContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();

    //=========================================================================================================

    /**
     * ~~~~~~~~~~~~~~~~~~~ LOADING FROM AZURE:  ~~~~~~~~~~~~~~~~~~~
     */

    public static void loadFromAzure() throws IOException, ClassNotFoundException {

        if (communityContainer.exists()) {
            System.out.println("~~~ loading all Users-data from azure ~~~");
            BlobClient usersBlob = communityContainer.getBlobClient("communityblob"); // Maak een blobclient aan voor Users
            if (usersBlob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                usersBlob.download(baos); // download van de blob, overschrijven ja.
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Map<String, User> loadedUsers = (Map<String, User>) ois.readObject();
                Community.setUserMap(loadedUsers);

                System.out.println("~~~ Succesfully loaded all Users-data from azure ~~~");
                baos.close();
                bais.close();
                ois.close();
            }
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * ~~~~~~~~~~~~~~~~~~~ SAVING TO AZURE:  ~~~~~~~~~~~~~~~~~~~
     * tijdens afsluiten
     */
    public static void uploadToAzure() throws IOException {
        System.out.println("~~~ START saveToAzure ~~~");
        if (!communityContainer.exists()) {
            communityContainer.create();
        }// creeert een blobcontainer als deze nog niet bestond

        BlobClient usersBlobClient = communityContainer.getBlobClient("communityblob"); // Maak een blobclient aan voor Users
        System.out.println("~~~ getting usermap from Cumminity ~~~");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        Map<String, User> userMapToSave = Community.getUserMap(); //locally save the map to be uploaded
        oos.writeObject(userMapToSave); //put the item to be saved into ObjectOutputStream


        byte[] bytez = baos.toByteArray();   // binair maken
        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);

        System.out.println("~~~ saving all Users-data to azure ~~~");
        usersBlobClient.upload(bais, bytez.length, true);  // upload naar de blob, overschrijven ja.
        baos.close();
        oos.close();
        bais.close(); // allestreams closen


    }
}

