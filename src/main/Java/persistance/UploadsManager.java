//package main.persistance;
//
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobContainerClientBuilder;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//
//public class UploadsManager {
//    private final static String ENDPOINT = "https://bepblobstorage.blob.core.windows.net/";
//    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=co&sp=rwdlacx&se=2020-09-06T03:04:58Z&st=2020-05-06T19:04:58Z&spr=https&sig=SzuMyPuiRCrN9WqnkRZz3u9cl%2BSKTtpaPir3Fx5ANRo%3D";
//    private final static String CONTAINER = "uploadscontainer";
//
//    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder()
//            .endpoint(ENDPOINT)
//            .sasToken(SASTOKEN)
//            .containerName(CONTAINER)
//            .buildClient();
//
//    public static EncodedBase64 loadEncodedUploadFromAzure(String uploadId) {
//        if (!blobContainer.exists())
//            throw new IllegalStateException("Container does not exist!");
//
//        BlobClient blob = blobContainer.getBlobClient(uploadId);
//
//        if (!blob.exists())
//            throw new IllegalStateException("Blob does not exist!");
//
//        byte[] bytez = null;
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//            blob.download(baos);
//            bytez = baos.toByteArray();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        return new EncodedBase64(new String(bytez));
//    }
//
//    public static DecodedBase64 loadDecodedUploadFromAzure(String uploadId) {
//        String base64str = loadEncodedUploadFromAzure(uploadId).getBase64str();
//
//        int prefixEndIndex = base64str.indexOf(";base64");
//        String mime = base64str.substring(5, prefixEndIndex);
//        byte[] bytez = Base64.getDecoder().decode(base64str.substring(prefixEndIndex + 8));
//
//        return new DecodedBase64(bytez, mime);
//    }
//
//    public static String saveUploadToAzure(EncodedBase64 upload) {
//        if (!blobContainer.exists())
//            blobContainer.create();
//
//        String base64str = upload.getBase64str();
//
//        long[] idParts = { System.currentTimeMillis(), Math.abs(base64str.hashCode()) };
//        String uniqueId = String.valueOf(idParts[0]).concat(String.valueOf(idParts[1]));
//
//        BlobClient blob = blobContainer.getBlobClient(uniqueId);
//
//        byte[] bytez = base64str.getBytes();
//
//        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytez)) {
//            blob.upload(bais, bytez.length, true);
//            return uniqueId;
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//        return null;
//    }
//}
