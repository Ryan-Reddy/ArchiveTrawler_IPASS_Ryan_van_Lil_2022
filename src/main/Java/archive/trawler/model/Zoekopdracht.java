package archive.trawler.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/** De zoekopdracht die de User heeft aangemaakt, deze hoeft niet per definitie opgeslagen te worden.
 * De gebruiker mag hier uiteraard wel voor kiezen.
 *
 */
@Data public class Zoekopdracht implements Serializable {
    private @Getter @Setter List<Archief> archiefKeuzes;
    private @Getter @Setter String keyWords; // de hoofdZoekQuery

//    private @Getter @Setter String voorNaam;      // TODO wellicht in een later stadium, lastig met meerdere archieven
//    private @Getter @Setter String achterNaam;    //  lastig met meerdere archieven
//    private @Getter @Setter int jaarVan;          //  lastig met meerdere archieven
//    private @Getter @Setter int jaarTot;          //  lastig met meerdere archieven
    private @Getter @Setter String zoekOpdrachtID;        //TODO automatiseer zoekOpdrachtID
    private @Getter @Setter User userID;
    private @Getter @Setter List<Websites> zoekresultatenIndividueel; // TODO functie elders

    /** De zoekopdracht van de gebruiker, deze is aangemaakt
     * @param archiefKeuzes keuze van de archieven
     * @param keyWords
//     * @param voorNaam
//     * @param achterNaam
//     * @param jaarVan
//     * @param jaarTot
//     * @param zoekOpdrachtID
     * @param user de gebruiker die eigenaar is van deze zoekopdracht
//     * @param zoekresultatenIndividueel
     */
    public Zoekopdracht(List<Archief> archiefKeuzes, String keyWords,
//                        String voorNaam, String achterNaam, int jaarVan, int jaarTot,
                        User user
//                        List<Websites> zoekresultatenIndividueel // TODO wordt geimplementeerd met zoekopdracht uitvoeren niet in de contructor
    ) {
        this.archiefKeuzes = archiefKeuzes;
        this.keyWords = keyWords;
//        this.voorNaam = voorNaam;
//        this.achterNaam = achterNaam;
//        this.jaarVan = jaarVan;
//        this.jaarTot = jaarTot;
        this.userID = user;
//        this.zoekOpdrachtID = genereerZoekOpdrachtID(this.userID);
//        this.zoekresultatenIndividueel = zoekresultatenIndividueel;  // TODO wordt geimplementeerd met zoekopdracht uitvoeren niet in de contructor
    }

    private String genereerZoekOpdrachtID(User user) {
            int max = 999;
            int min = 100;
            long epochSecond = Instant.now().getEpochSecond(); //Long = 1450879900
            return (String) (user.getNaam() + "_" + epochSecond + (int) (min + (Math.random() * max)));
        }

        private String genereerZoekURI() {    // TODO schrijf functie maak zoekopdracht
            return "write function plz";
        }
    }

// TODO schrijf functie maak zoekopdrachtzoekresultaten.. wellicht in de klasse ZoekResultaat

