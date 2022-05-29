package main;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Zoekopdrachten {
    private List<Archieven> archiefKeuzes;
    private String keyWords;
    private String voorNaam;
    private String achterNaam;
    private Year jaarVan;
    private Year jaarTot;
    private String zoekOpdrachtID;
    private String userID;

    public Zoekopdrachten(List<Archieven> archiefKeuzes, String keyWords, String voorNaam, String achterNaam,
                          Year jaarVan, Year jaarTot, String zoekOpdrachtID, String userID) {
        this.archiefKeuzes = archiefKeuzes;
        this.keyWords = keyWords;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.jaarVan = jaarVan;
        this.jaarTot = jaarTot;
        this.zoekOpdrachtID = zoekOpdrachtID;
        this.userID = userID;
    }

    public List<Archieven> getArchiefKeuzes() {
        return archiefKeuzes;
    }

    public void setArchiefKeuzes(List<Archieven> archiefKeuzes) {
        this.archiefKeuzes = archiefKeuzes;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public Year getJaarVan() {
        return jaarVan;
    }

    public void setJaarVan(Year jaarVan) {
        this.jaarVan = jaarVan;
    }

    public Year getJaarTot() {
        return jaarTot;
    }

    public void setJaarTot(Year jaarTot) {
        this.jaarTot = jaarTot;
    }

    public String getZoekOpdrachtID() {
        return zoekOpdrachtID;
    }

    public void setZoekOpdrachtID(String zoekOpdrachtID) {
        this.zoekOpdrachtID = zoekOpdrachtID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zoekopdrachten that = (Zoekopdrachten) o;
        return Objects.equals(archiefKeuzes,
                              that.archiefKeuzes) && Objects.equals(keyWords,
                                                                    that.keyWords) && Objects.equals(voorNaam,
                                                                                                     that.voorNaam) && Objects.equals(achterNaam,
                                                                                                                                      that.achterNaam) && Objects.equals(jaarVan,
                                                                                                                                                                         that.jaarVan) && Objects.equals(jaarTot,
                                                                                                                                                                                                         that.jaarTot) && Objects.equals(zoekOpdrachtID,
                                                                                                                                                                                                                                         that.zoekOpdrachtID) && Objects.equals(userID,
                                                                                                                                                                                                                                                                                that.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archiefKeuzes,
                            keyWords,
                            voorNaam,
                            achterNaam,
                            jaarVan,
                            jaarTot,
                            zoekOpdrachtID,
                            userID);
    }

    @Override
    public String toString() {
        return "Zoekopdrachten{" +
                "archiefKeuzes=" + archiefKeuzes +
                ", keyWords='" + keyWords + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", achterNaam='" + achterNaam + '\'' +
                ", jaarVan=" + jaarVan +
                ", jaarTot=" + jaarTot +
                ", ID='" + zoekOpdrachtID + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
