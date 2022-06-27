package archive.trawler.persistance;

import archive.trawler.security.DecodedBase64;
import archive.trawler.security.EncodedBase64;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
/** Deze klasse zorgt voor individuele blobs voor elk aangemaakte account in de uploadcontainer*/

public class UploadsManager {
    private final static String ENDPOINT = "https://ryanreddyipass.blob.core.windows.net/";
    private final static String SASTOKEN = "sp=racwdli&st=2022-06-27T12:11:40Z&se=2032-06-27T20:11:40Z&spr=https&sv=2021-06-08&sr=c&sig=i%2FYZLSbI5JckGxzQ%2BSh3suduRszln5gCIkpk53JLXBA%3D";
    private final static String CONTAINER = "uploadscontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    public static EncodedBase64 loadEncodedUploadFromAzure(String uploadId) {
        if (!blobContainer.exists())
            throw new IllegalStateException("Container does not exist!");


        BlobClient blob = blobContainer.getBlobClient(uploadId);

        if (!blob.exists())
            throw new IllegalStateException("Blob does not exist!");

        byte[] bytez = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            blob.download(baos);
            bytez = baos.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new EncodedBase64(new String(bytez));
    }

    public static DecodedBase64 loadDecodedUploadFromAzure(String uploadId) {
        String base64str = loadEncodedUploadFromAzure(uploadId).getBase64str();

        int prefixEndIndex = base64str.indexOf(";base64");
        String mime = base64str.substring(5, prefixEndIndex);
        byte[] bytez = Base64.getDecoder().decode(base64str.substring(prefixEndIndex + 8));

        return new DecodedBase64(bytez, mime);
    }

    /**
     * Deze uploadToAzure upload elke image apart naar de backup
     * Handig voor profielfoto's bijvoorbeeld
     * @param upload upload van n image of andere file
     * @return
     */
    public static String uploadToAzure(EncodedBase64 upload) {
        if (!blobContainer.exists())
            blobContainer.create(); //creeert de blob container als deze nog niet bestond

        String base64str = upload.getBase64str();

        long[] idParts = { System.currentTimeMillis(), Math.abs(base64str.hashCode()) };
        String uniqueId = String.valueOf(idParts[0]).concat(String.valueOf(idParts[1]));

        BlobClient blob = blobContainer.getBlobClient(uniqueId); // elke upload krijgt zn eigen blob !

        byte[] bytez = base64str.getBytes();

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytez)) {
            blob.upload(bais, bytez.length, true);
            return uniqueId;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
