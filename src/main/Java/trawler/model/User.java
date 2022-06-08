package trawler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String userName;
    private String emailAdres;
    private String voorNaam;
    private String achterNaam;
    private String wachtwoord;
    private String userID;
    private String role;
    private ArrayList<Zoekopdracht> zoekertjes;


    public User(String userName, String emailAdres, String voorNaam, String achterNaam, String wachtwoord,
                String role) {

        if(Community.getCommunity().getAccount(userName)==null) {
        this.userName = userName;
        this.emailAdres = emailAdres;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.wachtwoord = wachtwoord;
        this.userID = userName + emailAdres;
        this.role = role;
        Community.getCommunity().addAccount(this);}

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Zoekopdracht> getZoekertjes() {
        return zoekertjes;
    }

    public void setZoekertjes(ArrayList<Zoekopdracht> zoekertjes) {
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
        return Objects.equals(userName,
                              user.userName) && Objects.equals(emailAdres,
                                                               user.emailAdres) && Objects.equals(voorNaam,
                                                                                                  user.voorNaam) && Objects.equals(achterNaam,
                                                                                                                                   user.achterNaam) && Objects.equals(wachtwoord,
                                                                                                                                                                      user.wachtwoord) && Objects.equals(userID,
                                                                                                                                                                                                         user.userID) && Objects.equals(zoekertjes,
                                                                                                                                                                                                                                        user.zoekertjes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName,
                            emailAdres,
                            voorNaam,
                            achterNaam,
                            wachtwoord,
                            userID,
                            zoekertjes);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailAdres='" + emailAdres + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", achterNaam='" + achterNaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", userID='" + userID + '\'' +
                ", zoekertjes=" + zoekertjes +
                '}';
    }
}
