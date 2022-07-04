package archive.trawler.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

/**Zijn de individuele website links uit de zoek resultaten van de gebruiker.
 * @resultaatURL URL uit de zoekresultaten.
 * @thisUser User die hoort bij deze zoekopdracht.
 * @aantalKeerBezocht aantalkeer dat deze link is aangeklikt, default is 0
 * @datumBezocht Lijst met data waarop de bezoeker deze link heeft aangeklikt
 * */
@Data
public class Website {
    private @Getter @Setter String resultaatURL;
    private @Getter @Setter  int aantalKeerBezocht;
    private @Getter @Setter  User userID;
    private @Getter @Setter ArrayList<LocalDateTime> datumBezocht = new ArrayList<LocalDateTime>();

    /** Constructor zonder "aantalkeerbezocht Param." default is dan 0.
     * @param resultaatURL  URL uit de zoekresultaten.
     * @param thisUser      User die hoort bij deze zoekopdracht.
     */
    public Website(String resultaatURL, User thisUser) {
        this.resultaatURL = resultaatURL;
        this.aantalKeerBezocht = 0;
        this.userID = thisUser;
    }

    /** Constructor met custom aantalkeerbezocht.
     * @param resultaatURL URL uit de zoekresultaten.
     * @param thisUser User die hoort bij deze zoekopdracht.
     * @param aantalKeerBezocht aantalkeer dat deze link is aangeklikt, default is 0
     */
    public Website(String resultaatURL, User thisUser, int aantalKeerBezocht) {
        this.resultaatURL = resultaatURL;
        this.aantalKeerBezocht = aantalKeerBezocht;
        this.userID = thisUser;
    }

    /** Telt de keren dat ArchiveTrawler de User heeft doorgelinkt, voegt ook een datalog aan de datumBezocht lijst toe.*/
    public void websiteDoorgelinkt(){
        this.aantalKeerBezocht += 1;
        datumBezocht.add(LocalDateTime.now());
    }
    /** Haalt een lijst met alle keren dat de User deze specifieke link heeft aangeklikt. */
    public ArrayList<LocalDateTime> getDatumBezocht() {
        return datumBezocht;
    }
}

