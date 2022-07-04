package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * De zoekopdracht die de User heeft aangemaakt, deze hoeft niet per definitie opgeslagen te worden.
 * De gebruiker mag hier uiteraard wel voor kiezen.
 */
@Data
public class Zoekopdracht implements Serializable {
    private @Getter
    @Setter ArrayList<Archief> archiefKeuzes;
    private @Getter
    @Setter String BaseURI;
    private @Getter
    @Setter String keyWords; // de hoofdZoekQuery

    private @Getter
    @Setter String voorNaam;      // TODO wellicht in een later stadium, lastig met meerdere archieven
    private @Getter
    @Setter String tussenVoegsel;    //  lastig met meerdere archieven
    private @Getter
    @Setter String achterNaam;    //  lastig met meerdere archieven
    private @Getter
    @Setter int jaarVan;          //  lastig met meerdere archieven
    private @Getter
    @Setter int jaarTot;          //  lastig met meerdere archieven
    private @Getter
    @Setter String zoekOpdrachtID;        //TODO automatiseer zoekOpdrachtID
    private @Getter
    @Setter int userOwner;
    private @Getter
    @Setter ArrayList<String> zoekUris; // TODO functie elders
    private @Getter
    @Setter List<Websites> zoekresultatenIndividueel; // TODO functie elders

    /**
     * De zoekopdracht van de gebruiker, deze is aangemaakt
     *
     * @param archiefKeuzes             keuze van de archieven
     * @param keyWords
     * @param user                      de gebruiker die eigenaar is van deze zoekopdracht
     * @param @redunant voorNaam
     * @param @redunant achterNaam
     * @param @redunant tussenvoegsel
     * @param @redunant jaarVan
     * @param @redunant jaarTot
     * @param @redunant zoekOpdrachtID
     * @param @redunant zoekresultatenIndividueel
     */
    public Zoekopdracht(ArrayList<Archief> archiefKeuzes, String keyWords,
//                        String voorNaam, String tussenvoegsel, String achterNaam, int jaarVan, int jaarTot,
                        int user
    ) {
        this.archiefKeuzes = archiefKeuzes;
        this.keyWords = keyWords;
//        this.voorNaam = voorNaam;
//        this.tussenVoegsel = tussenvoegsel;
//        this.achterNaam = achterNaam;
//        this.jaarVan = jaarVan;
//        this.jaarTot = jaarTot;
        this.userOwner = user;
        this.zoekOpdrachtID = genereerZoekOpdrachtID(this.userOwner);

        Community.getZoekOpdrachtMap().put(this.zoekOpdrachtID,this);
        this.genereerZoekURIs();
    }

    /**
     * genereerZoekOpdrachtID
     * @param userID de eigenaar, wordt gebruikt in generatie van het token
     * @return een unieke string, gebruikt om de zoekopdracht op te slaan.
     */
    private String genereerZoekOpdrachtID(int userID) {
        int max = 999;
        int min = 100;
        long epochSecond = Instant.now().getEpochSecond(); //Long = 1450879900
        return userID + "_" + epochSecond + (int) (min + (Math.random() * max));
    }

    /**
     * Deze methode genereert een lijst aan zoek URIs gebaseerd op de query
     * Per archief is dit verschillend dus dit wordt dan ook ik de klasse Archief geregeld.
     */
    private void genereerZoekURIs() {    // TODO schrijf functie maak zoekopdracht
        ArrayList<String> nieuweUris = new ArrayList<String>();
        String query = keyWords;
//        String query = achterNaam;
//        if(!tussenVoegsel.isBlank()) query = tussenVoegsel + "%20" + query;
//        if(!voorNaam.isBlank()) query = voorNaam + "%20" + query;
//        if(keyWords != null) query = keyWords + "%20" + query;

        // loop over de archiefkeuzes
        for (Archief archief : archiefKeuzes) {
            nieuweUris.add(archief.createLink(query, this.userOwner));
        }
        this.setZoekUris(nieuweUris);
    }
}

// TODO schrijf functie maak zoekopdrachtzoekresultaten.. wellicht in de klasse ZoekResultaat

