package archive.trawler.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class Zoekopdracht implements Serializable {
    private List<Archief> archiefKeuzes;
    private String keyWords;
    private String voorNaam;
    private String achterNaam;
    private int jaarVan;
    private int jaarTot;
    private String zoekOpdrachtID;        //TODO automatiseer zoekOpdrachtID
    private String userID;

    public Zoekopdracht(List<Archief> archiefKeuzes, String keyWords, String voorNaam, String achterNaam, int jaarVan, int jaarTot, String zoekOpdrachtID, String userID) {
        this.archiefKeuzes = archiefKeuzes;
        this.keyWords = keyWords;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.jaarVan = jaarVan;
        this.jaarTot = jaarTot;
        this.zoekOpdrachtID = zoekOpdrachtID;
        this.userID = userID;
    }
}
