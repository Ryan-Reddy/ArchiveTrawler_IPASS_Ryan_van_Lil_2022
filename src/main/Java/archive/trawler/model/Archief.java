package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/** Deze klasse behoud alle informatie van individuele (externe) archieven.
 **/
@Data
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
     * @param keyWords Hoofd keywords query
     * @param userID ID van de user
     * @return String met de link (nog niet web safe)
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
     }

    /**
     * Maak de url Web Safe, door gevaarlijke characters weg te halen, dmv het implementeren van de chars in UTF_8
     * Denk aan spatie of andere symbolen.
     * @param value de potentieel 'onveilige' URI
     * @return String met veilige characters.
     */
    private static String encodeStringNaarURLveilig(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
