//package main.webservices;
//
//import nl.hu.bep.referenceproject.persistence.DecodedBase64;
//import nl.hu.bep.referenceproject.persistence.UploadsManager;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//
//@Path("/files/{fileId}")
//public class FileResource {
//
//    @GET
//    public Response getFile(@PathParam("fileId") String fileId) {
//        try {
//            main.Java.trawler.persistance.DecodedBase64 file = UploadsManager.loadDecodedUploadFromAzure(fileId);
//
//            return Response
//                    .status(Response.Status.OK)
//                    .entity(file.bytez)
//                    .type(file.mime)
//                    .build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
//}
