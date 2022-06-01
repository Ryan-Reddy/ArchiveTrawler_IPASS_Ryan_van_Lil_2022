package main.Java.model;

import java.time.LocalDateTime;
import java.util.*;

public class Websites {
    private String resultaatURL;
    private int aantalKeerBezocht;
    private String userID;
    private ArrayList<LocalDateTime> datumBezocht = new ArrayList<LocalDateTime>();


    public Websites(String resultaatURL, String userID) {
        this.resultaatURL = resultaatURL;
        this.aantalKeerBezocht = 0;
        this.userID = userID;
    }

    public void websiteDoorgelinkt(){
        this.aantalKeerBezocht += 1;
        datumBezocht.add(LocalDateTime.now());
    }

    public String getResultaatURL() {
        return resultaatURL;
    }

    public void setResultaatURL(String resultaatURL) {
        this.resultaatURL = resultaatURL;
    }

    public int getAantalKeerBezocht() {
        return aantalKeerBezocht;
    }

    public void setAantalKeerBezocht(int aantalKeerBezocht) {
        this.aantalKeerBezocht = aantalKeerBezocht;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<LocalDateTime> getDatumBezocht() {
        return datumBezocht;
    }

    public void setDatumBezocht(ArrayList<LocalDateTime> datumBezocht) {
        this.datumBezocht = datumBezocht;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Websites websites = (Websites) o;
        return aantalKeerBezocht == websites.aantalKeerBezocht && Objects.equals(resultaatURL,
                                                                                 websites.resultaatURL) && Objects.equals(userID,
                                                                                                                          websites.userID) && Objects.equals(datumBezocht,
                                                                                                                                                             websites.datumBezocht);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultaatURL,
                            aantalKeerBezocht,
                            userID,
                            datumBezocht);
    }

    @Override
    public String toString() {
        return "Websites{" +
                "resultaatURL='" + resultaatURL + '\'' +
                ", aantalKeerBezocht=" + aantalKeerBezocht +
                ", userID='" + userID + '\'' +
                ", datumBezocht=" + datumBezocht +
                '}';
    }
}

