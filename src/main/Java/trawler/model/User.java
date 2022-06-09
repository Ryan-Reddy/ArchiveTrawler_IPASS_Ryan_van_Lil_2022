package trawler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String emailAdres;
    private String voorNaam;
    private String achterNaam;
    private String wachtwoord;
    private String userID;
    private String role;
    private List<AlleZoekopdracht> zoekertjes;



    public User(String emailAdres, String voorNaam, String achterNaam, String wachtwoord, String role) {

        if (!Community.getCommunity().getUsers().containsKey(emailAdres)) {
            this.emailAdres = emailAdres;
            this.voorNaam = voorNaam;
            this.achterNaam = achterNaam;
            this.wachtwoord = wachtwoord;
            this.userID = String.valueOf(Community.getCommunitySize()) + 1;
            this.role = role;
            Community.getCommunity().addAccount(this);
        }

    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
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

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public List<AlleZoekopdracht> getZoekertjes() {
        return zoekertjes;
    }

    public void setZoekertjes(ArrayList<AlleZoekopdracht> zoekertjes) {
        this.zoekertjes = zoekertjes;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //    public boolean verifyUser(String userName,String userPassword) {
//        for
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(emailAdres, user.emailAdres) && Objects.equals(voorNaam, user.voorNaam) && Objects.equals(achterNaam, user.achterNaam) && Objects.equals(wachtwoord, user.wachtwoord) && Objects.equals(userID, user.userID) && Objects.equals(zoekertjes, user.zoekertjes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAdres, voorNaam, achterNaam, wachtwoord, userID, zoekertjes);
    }

    @Override
    public String toString() {
        return "User{" + "userName='" + '\'' + ", emailAdres='" + emailAdres + '\'' + ", voorNaam='" + voorNaam + '\'' + ", achterNaam='" + achterNaam + '\'' + ", wachtwoord='" + wachtwoord + '\'' + ", userID='" + userID + '\'' + ", zoekertjes=" + zoekertjes + '}';
    }
}
