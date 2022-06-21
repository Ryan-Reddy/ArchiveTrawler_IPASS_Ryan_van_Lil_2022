package archive.trawler.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** De zoekopdracht die de User heeft aangemaakt, deze hoeft niet per definitie opgeslagen te worden.
 * De gebruiker mag hier uiteraard wel voor kiezen.
 *
 */
@Data public class Zoekopdracht implements Serializable {
    private @Getter @Setter List<Archief> archiefKeuzes;
    private @Getter @Setter String keyWords;
    private @Getter @Setter String voorNaam;
    private @Getter @Setter String achterNaam;
    private @Getter @Setter int jaarVan;
    private @Getter @Setter int jaarTot;
    private @Getter @Setter String zoekOpdrachtID;        //TODO automatiseer zoekOpdrachtID
    private @Getter @Setter String userID;
    private @Getter @Setter List<Websites> zoekresultatenIndividueel;

    /** De zoekopdracht van de gebruiker, deze is aangemaakt
     * @param archiefKeuzes keuze van de archieven
     * @param keyWords
     * @param voorNaam
     * @param achterNaam
     * @param jaarVan
     * @param jaarTot
     * @param zoekOpdrachtID
     * @param userID
     * @param zoekresultatenIndividueel
     */
    public Zoekopdracht(List<Archief> archiefKeuzes, String keyWords, String voorNaam, String achterNaam, int jaarVan, int jaarTot, String zoekOpdrachtID, String userID, List<Websites> zoekresultatenIndividueel) {
        this.archiefKeuzes = archiefKeuzes;
        this.keyWords = keyWords;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.jaarVan = jaarVan;
        this.jaarTot = jaarTot;
        this.zoekOpdrachtID = zoekOpdrachtID;
        this.userID = userID;
        this.zoekresultatenIndividueel = zoekresultatenIndividueel;
    }
    // TODO schrijf functie maak zoekopdracht/zoekresultaten
}
