package archive.trawler.webservices.SearchAdvanced;


import archive.trawler.model.Archief;
import archive.trawler.model.User;
import archive.trawler.model.Zoekopdracht;
import archive.trawler.persistance.Community;
import archive.trawler.webservices.dto.SearchAdvancedInput;
import org.json.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("search-advanced-service")
public class SearchAdvancedResource {
    /**
     * advanced search, handled de zoekquery vanuit zoeken-advanced formulier op de website.
     *
     * @param searchQuery searchquery van het type JSON > vorm: DTO SearchAdvancedInput
     */
    @POST
    @RolesAllowed("user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response advancedSearchProcess(SearchAdvancedInput searchQuery, @Context SecurityContext sc) {
        System.out.println("advancedSearchProcess");
        try {
            // 1 check of het een user is die ingelogd is:
            if (sc.getUserPrincipal() instanceof User) {
                User theUser = (User) sc.getUserPrincipal();

                //  2.1 define query
                String keyWords = new String(searchQuery.keywords);
                /** VOOR EEN LATERE SPRINT:
                 //                String achterNaam = new String(searchQuery.achternaam);
                 //                String voorNaam = new String(searchQuery.voorNaam);
                 //                String tussenVoegsel = new String(searchQuery.tussenvoegsel);
                 //                int jaarVan = searchQuery.jaarVan;
                 //                int jaarTot = searchQuery.jaarTot;
                 */
                //  2.2 check for null

                //  2.3 Definieer lijst met archiefkeuzes
//                boolean amsArchief = searchQuery.archiveAmsterdam;
//                boolean openArchief = searchQuery.openArchive;
                ArrayList<Archief> archiefKeuzesArrayList = new ArrayList<>();

                if (searchQuery.archiveAmsterdam) archiefKeuzesArrayList.add(Community.getArchiefByName("amsArchief"));
                if (searchQuery.openArchive) archiefKeuzesArrayList.add(Community.getArchiefByName("openArchief"));

                //  2.3 create links

                Zoekopdracht zoekopdracht = new Zoekopdracht(archiefKeuzesArrayList, keyWords,
//                        tussenVoegsel, voorNaam, achterNaam, 1000, 2022, // is lastig met meerdere archieven
                        theUser);
                Map<String, JSONObject> messages = new HashMap<>();

                for (String zoekUri : zoekopdracht.getZoekUris()) {
//                    String resultaat = fetchSearchResult(zoekUri);
//                    JSONObject json = new JSONObject(resultaat); // Convert jsontext naar object
//                    String prettyJson = json.toString(4);
//                    System.out.println(prettyJson); // Print it with specified indentation
//                    JSONObject resultaat = fetchSearchResult(zoekUri);
//                    System.out.println(resultaat);
//                    JSONObject json = new JSONObject(resultaat); // Convert jsontext naar object
//                    String prettyJson = fetchSearchResult(zoekUri).toString(4);
//                    System.out.println(prettyJson); // Print it with specified indentation

                return Response.ok().entity(fetchSearchResult(zoekUri).toString()).build();
//                    messages.put("zoekopdracht",
//                            fetchSearchResult(zoekUri).toString()
//                    );
                }

                //  3. run fetch in backend

//                return Response.ok(messages).build();
                return Response.ok().entity(messages).build();


                // TODO schrijf handle de input van searchQuery
                //  4. compare to local files/create local files
                //  5. create difference log
                //  6. return the search results incl changelog
                //  create fetchURI per archief in searchQuery > archiveAmsterdam & archiveNoordHolland
                //  labels: advancedSearchProcess
            }
            return Response.status(Response.Status.UNAUTHORIZED).entity("You do need to be logged in, create a free account, and search away!").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception).build();
        }
    }

    public JSONObject fetchSearchResult(String theURL) throws IOException {
        URL url = new URL(theURL);
        InputStream is = url.openStream();
        int ptr = 0;
        StringBuffer buffer = new StringBuffer();
        while ((ptr = is.read()) != -1) {
            buffer.append((char) ptr); //append elke character 1 voor 1
        }
        return new JSONObject(buffer.toString());
    }
}
