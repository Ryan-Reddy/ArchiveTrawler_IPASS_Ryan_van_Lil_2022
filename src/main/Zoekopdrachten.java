package main;

import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;

public class Zoekopdrachten {
    private String keyWords;
    private String voorNaam;
    private String achterNaam;
    private Year jaarVan;
    private Year jaarTot;
    private ArrayList<Archieven> archiefKeuzes;

    public Zoekopdrachten(String keyWords, String voorNaam, String achterNaam, Year jaarVan, Year jaarTot, ArrayList<Archieven> archiefKeuzes ) {
        this.keyWords = keyWords;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.jaarVan = jaarVan;
        this.jaarTot = jaarTot;
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

    public ArrayList<Archieven> getArchiefKeuzes() {
        return archiefKeuzes;
    }

    public void setArchiefKeuzes(ArrayList<Archieven> archiefKeuzes) {
        this.archiefKeuzes = archiefKeuzes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zoekopdrachten)) return false;
        Zoekopdrachten that = (Zoekopdrachten) o;
        return Objects.equals(getKeyWords(), that.getKeyWords()) && Objects.equals(getVoorNaam(), that.getVoorNaam()) && Objects.equals(getAchterNaam(), that.getAchterNaam()) && Objects.equals(getJaarVan(), that.getJaarVan()) && Objects.equals(getJaarTot(), that.getJaarTot()) && Objects.equals(getArchiefKeuzes(), that.getArchiefKeuzes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyWords(), getVoorNaam(), getAchterNaam(), getJaarVan(), getJaarTot(), getArchiefKeuzes());
    }

    @Override
    public String toString() {
        return "Zoekopdrachten{" +
                "keyWords='" + keyWords + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", achterNaam='" + achterNaam + '\'' +
                ", jaarVan=" + jaarVan +
                ", jaarTot=" + jaarTot +
                ", archiefKeuzes=" + archiefKeuzes +
                '}';
    }
}
