package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private String userName;
    private String emailAdres;
    private String voorNaam;
    private String achterNaam;
    private String wachtwoord;
    private String userID;
    private ArrayList<Zoekopdrachten> zoekertjes;

    public User(String userName, String emailAdres, String voorNaam, String achterNaam, String wachtwoord) {
        this.userName = userName;
        this.emailAdres = emailAdres;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.wachtwoord = wachtwoord;
        this.userID = userName + emailAdres;
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

    public String getID() {
        return userID;
    }

    public void setID(String userID) {
        this.userID = userID;
    }

    public List<Zoekopdrachten> getZoekertjes() {
        return zoekertjes;
    }
//
//    public void setZoekertjes(ArrayList<Zoekopdrachten> zoekertjes) {
//        this.zoekertjes = zoekertjes;
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
