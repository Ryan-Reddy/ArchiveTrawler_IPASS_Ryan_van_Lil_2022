package archive.trawler.persistance;

import archive.trawler.model.User;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.*;
import java.util.Map;

/**PersistanceManager
 * Een zeer gevoelige bezigheid,
 * Kan erg makkelijk stuk gaan.
 * Hier een paar tips voor basic troubleshooting: <br>
 * - zorg er voor dat alle klassen en gerelateerde klassen die opgeslagen worden "implements Serializable" zijn.<br>
 * - importeer de waarde die je wil opslaan ook in je saveToAzure() functie zelf ie List listOmOpTeSlaan = Users.getList();<br>
 * - loop zeker nog alle SAStokens/blobtokens/containernamen goed na.<br>
 * Het probleem zal nu wel opgelost zijn, anders ligt het aan je model zelf **/

public class PersistanceManager {
    private static final String ENDPOINT = "https://ryanreddyipass.blob.core.windows.net/";
    private static final String SASTOKEN = "?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-22T01:55:53Z&st=2022-06-21T17:55:53Z&spr=https&sig=o6Ovg7nmpYR%2Beyll00G399spw9fcR4XdH1y%2FVTs9W34%3D";
    private static final String CONTAINER = "communitycontainer";
//        private static final String usersBlobName = "communityblob";
    public static final BlobContainerClient communityContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();
//   /* BACKUP: */
    private static final String SASTOKENbackup = "?sv=2021-06-08&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2030-06-25T09:17:05Z&st=2022-06-25T01:17:05Z&spr=https&sig=BvFj9QJmLSkWtWKFqAUVo%2BEv%2FCzgCZ%2BXkjDGyrw9d7c%3D";
    private static final String CONTAINERbackup = "communitycontainerbackup";
        public static final String usersBlobNamebackup = "communityblobbackup";
    public static final BlobContainerClient communityContainerbackup = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKENbackup).containerName(CONTAINERbackup).buildClient();

    //=========================================================================================================

    /**
     * ~~~~~~~~~~~~~~~~~~~ LOADING FROM AZURE:  ~~~~~~~~~~~~~~~~~~~
     * Tijdens opstarten van de app.
     */

    public static void loadFromAzure() throws IOException, ClassNotFoundException {

        if (communityContainer.exists()) {
            System.out.println("[ AZURE ] [ DOWNLOAD ] ~~~ loading all Users-data from azure ~~~");
            BlobClient usersBlob = communityContainer.getBlobClient("communityblob"); // Maak een blobclient aan voor Users
            if (usersBlob.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                usersBlob.download(baos); // download van de blob, overschrijven ja.
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Map<String, User> loadedUsers = (Map<String, User>) ois.readObject();
                Community.setUserMap(loadedUsers);


                System.out.println("[ AZURE ] [ DOWNLOAD ] ~~~ Succesfully loaded all Users-data from azure ~~~");
                System.out.println("[ AZURE ] [ DOWNLOAD ] ~~~ Updated usermap: " + Community.getUserMap());
                baos.close();
                bais.close();
                ois.close();
            }
        System.out.println("[ AZURE ] [ DOWNLOAD ] ~~~ START-up BACKUP-saveToAzure ~~~");
            uploadToAzure(usersBlobNamebackup,communityContainerbackup);     ////////  /////  /// /* BACKUP */ ///  /////  ////////
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * ~~~~~~~~~~~~~~~~~~~ SAVING TO AZURE:  ~~~~~~~~~~~~~~~~~~~
     * Tijdens afsluiten van de app.
     * En tijdens het opstarten ook een backup van de ingeladen data, deze loopt dus wel wat achter.
     * Dagelijkse herstart van de server wel aan te raden.
     */
    public static void uploadToAzure(String usersBlobName, BlobContainerClient blobContainerClient) throws IOException {
        System.out.println("[ AZURE ] [ UPLOAD ] ~~~ START saveToAzure ~~~");
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }// creeert een blobcontainer als deze nog niet bestond

        BlobClient usersBlobClient = blobContainerClient.getBlobClient(usersBlobName); // Maak een blobclient aan voor Users
        System.out.println("[ AZURE ] [ UPLOAD ] ~~~ getting usermap from Community ~~~");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        Map<String, User> userMapToSave = Community.getUserMap(); //locally save the map to be uploaded
        oos.writeObject(userMapToSave); //put the item to be saved into ObjectOutputStream


        byte[] bytez = baos.toByteArray();   // binair maken
        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);

        System.out.println("[ AZURE ] [ UPLOAD ] ~~~ saving all Users-data to azure ~~~");
        usersBlobClient.upload(bais, bytez.length, true);  // upload naar de blob, overschrijven ja.
        baos.close();
        oos.close();
        bais.close(); // allestreams afsluiten
    }


}

