package trawler.webservices;

//import nl.hu.bep.referenceproject.model.Account;
//import nl.hu.bep.referenceproject.model.Company;
//import nl.hu.bep.referenceproject.persistence.EncodedBase64;
//import nl.hu.bep.referenceproject.persistence.UploadsManager;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.io.StringReader;
//import java.util.AbstractMap;
//
//@Path("/accounts")
//public class AccountsResource {
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccountHalfJackson(String jsonBody) {
//        Account account = new Account();
//
//        try {
//            JsonObject object = Json.createReader(new StringReader(jsonBody)).readObject();
//            account.setUsername(object.getString("username"));
//            account.setFullname(object.getString("fullname"));
//            account.setAddress(object.getString("address"));
//            account.setAvatarBase64(object.getString("avatarBase64"));
//        } catch (Exception e) {
//            return Response
//                    .status(Response.Status.BAD_REQUEST)
//                    .entity(new AbstractMap.SimpleEntry<>("message", "Wrong JSON-requestbody!"))
//                    .build();
//        }
//
//        if (Company.getCompany().addAccount(account)) {
//            if (!account.getAvatarBase64().isEmpty()) {
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.saveUploadToAzure(base64);
//                account.setAvatarUploadId(uploadId);
//            }
//
//            return Response.ok(account).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//
////    @POST
////    @Consumes(MediaType.APPLICATION_JSON)
////    @Produces(MediaType.APPLICATION_JSON)
////    public Response createAccountFullJackson(Account account) {
////        if (Company.getCompany().addAccount(account)) {
////            if (!account.getAvatarBase64().isEmpty()) {
////                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
////                String uploadId = UploadsManager.saveUploadToAzure(base64);
////                account.setAvatarUploadId(uploadId);
////            }
////
////            return Response.ok(account).build();
////        } else {
////            return Response.status(Response.Status.CONFLICT).build();
////        }
////    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccountFormData(@FormParam("username") String uN, @FormParam("fullname") String fN, @FormParam("address") String addr, @FormParam("avatarBase64") String aB64) {
//        Account account = new Account(uN, fN, addr, aB64);
//
//        if (Company.getCompany().addAccount(account)) {
//            if (!account.getAvatarBase64().isEmpty()) {
//                EncodedBase64 base64 = new EncodedBase64(account.getAvatarBase64());
//                String uploadId = UploadsManager.saveUploadToAzure(base64);
//                account.setAvatarUploadId(uploadId);
//            }
//
//            return Response.ok(account).build();
//        } else {
//            return Response.status(Response.Status.CONFLICT).build();
//        }
//    }
//}
