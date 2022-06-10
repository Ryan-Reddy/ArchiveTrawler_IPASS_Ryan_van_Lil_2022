package persistance;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import trawler.model.Community;

import java.io.*;

public class PersistanceManager {
    private final static String ENDPOINT = "https://ryanreddyipass.blob.core.windows.net//";
    private final static String SASTOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacupitfx&se=2032-06-10T23:00:06Z&st=2022-06-10T15:00:06Z&spr=https&sig=xEw9S%2FLZCky2l1L7kVSQrfQ%2FsD3HpYjyAokp0gVT%2FLs%3D";
    private final static String CONTAINER = "communitycontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();


    public static void loadCommunityFromAzure() throws IOException, ClassNotFoundException {
        BlobContainerClient blobContainer = null;
        if (blobContainer.exists()) {
            BlobClient blob = blobContainer.getBlobClient("loaded_world");
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


    public static void saveCommunityFromAzure() throws IOException {
        BlobClient blob = blobContainer.getBlobClient("world_blob");
        Community communityToSave = Community.getCommunity();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(communityToSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);

        baos.close();
        oos.close();
        bais.close();
    }
}
