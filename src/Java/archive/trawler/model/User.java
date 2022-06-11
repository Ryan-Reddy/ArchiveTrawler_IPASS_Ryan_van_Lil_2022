package archive.trawler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String emailAdres;
//    private String voorNaam;
    private String naam;
    private String wachtwoord;
    private String userID;
    private String role;
    private List<AlleZoekopdracht> zoekertjes;



    public User(String emailAdres, String naam, String wachtwoord, String role) {

        if (!Community.getCommunity().getUsers().containsKey(emailAdres)) {
            this.emailAdres = emailAdres;
//            this.voorNaam = voorNaam;
            this.naam = naam;
            this.wachtwoord = wachtwoord;
            this.userID = String.valueOf(Community.getCommunitySize()) + 1;
            this.role = role;
            Community.getCommunity().addAccount(this);
        }

    }
    public static boolean removeShopper(String emailAdress) {
        for (User sh : Community.getCommunity().getUsersAsList()) {
            if(sh.getEmailAdres().equals(emailAdress)){
                Community.getCommunity().getUsersAsList().remove(sh);
                return true;
            }
        }
        return false;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }
//
//    public String getVoorNaam() {
//        return voorNaam;
//    }
//
//    public void setVoorNaam(String voorNaam) {
//        this.voorNaam = voorNaam;
//    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getEmailAdres(), user.getEmailAdres()) && Objects.equals(getNaam(), user.getNaam()) && Objects.equals(getWachtwoord(), user.getWachtwoord()) && Objects.equals(getUserID(), user.getUserID()) && Objects.equals(getRole(), user.getRole()) && Objects.equals(getZoekertjes(), user.getZoekertjes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAdres(), getNaam(), getWachtwoord(), getUserID(), getRole(), getZoekertjes());
    }

    @Override
    public String toString() {
        return "User{" +
                "emailAdres='" + emailAdres + '\'' +
                ", naam='" + naam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", userID='" + userID + '\'' +
                ", role='" + role + '\'' +
                ", zoekertjes=" + zoekertjes +
                '}';
    }
}
