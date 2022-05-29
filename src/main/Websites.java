package main;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Websites {
    private String resultaatURL;
    private int aantalKeerBezocht;
    private int websiteID;
    private String userID;
    private List<Date> datumBezocht;


    public Websites(String resultaatURL, int aantalKeerBezocht) {
        this.resultaatURL = resultaatURL;
        this.aantalKeerBezocht = aantalKeerBezocht;
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

    public int getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(int websiteID) {
        this.websiteID = websiteID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Date> getDatumBezocht() {
        return datumBezocht;
    }

    public void setDatumBezocht(List<Date> datumBezocht) {
        this.datumBezocht = datumBezocht;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Websites websites = (Websites) o;
        return aantalKeerBezocht == websites.aantalKeerBezocht && websiteID == websites.websiteID && Objects.equals(resultaatURL,
                                                                                                                    websites.resultaatURL) && Objects.equals(userID,
                                                                                                                                                             websites.userID) && Objects.equals(datumBezocht,
                                                                                                                                                                                                websites.datumBezocht);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultaatURL,
                            aantalKeerBezocht,
                            websiteID,
                            userID,
                            datumBezocht);
    }

    @Override
    public String toString() {
        return "Websites{" +
                "resultaatURL='" + resultaatURL + '\'' +
                ", aantalKeerBezocht=" + aantalKeerBezocht +
                ", websiteID=" + websiteID +
                ", userID='" + userID + '\'' +
                ", datumBezocht=" + datumBezocht +
                '}';
    }
}

