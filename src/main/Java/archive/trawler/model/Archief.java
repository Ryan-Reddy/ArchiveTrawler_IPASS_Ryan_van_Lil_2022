package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/** Deze klasse behoud alle informatie van individuele (externe) archieven.
 **/
public class Archief implements Serializable {
    private @Getter @Setter String naam;
    private @Getter @Setter String basisURI;
    /**
     * Deze klasse behoud alle informatie van individuele (externe) archieven.
     * @param naam Hierin staat de naam van het archief
     * @param basisURI Hierin staat de basis URI, waarop je vervolgens kan bouwen met queryParameters
     */
     public Archief(String naam, String basisURI) {
         this.naam = naam;
         this.basisURI = basisURI;
         Community.addArchiefToMap(this);
     }

    /**
     * Deze methode creeert een link vanuit de basis URI die hoort bij het archief.
     * @param keyWords
     * @param userID
     * @return
     */
     public String createLink(String keyWords, int userID ) {
         try {
             keyWords = encodeStringNaarURLveilig(keyWords);
             // get the baseURI
             String baseURI = this.getBasisURI();
             return baseURI.replace("{naam}",keyWords);

         } catch (Exception e) {
             e.printStackTrace();
             return "[ ERROR ] in Archief.createLink.. :" + e;
         }
//         return "[ ERROR ] in Archief.createLink..";

//                                  https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22van%20lil%22%7D&rows=250
//        new Archief("amsarchief","https://webservices.picturae.com/genealogy/person?apiKey=eb37e65a-eb47-11e9-b95c-60f81db16c0e&page=1&q={naam}&rows=1000&sort=score desc");
//        new Archief("openarchief","https://api.openarch.nl/1.0/records/search.json?name={naam}&lang=nl&number_show=100&sort=1&start=0&archive");
     }
    private static String encodeStringNaarURLveilig(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archief archief = (Archief) o;
        return Objects.equals(naam,
                              archief.naam) && Objects.equals(basisURI,
                                                                archief.basisURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam,
                            basisURI);
    }

    @Override
    public String toString() {
        return "Archief{" +
                "naam='" + naam + '\'' +
                ", APIofURIConstructor='" + basisURI + '\'' +
                '}';
    }
}
