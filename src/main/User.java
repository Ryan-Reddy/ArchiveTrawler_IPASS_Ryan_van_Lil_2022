package main;

import java.util.Objects;

public class User {
    private String userName;
    private String emailAdres;
    private String voorNaam;
    private String achterNaam;
    private String wachtwoord;

    public User(String userName, String emailAdres, String voorNaam, String achterNaam, String wachtwoord) {
        this.userName = userName;
        this.emailAdres = emailAdres;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.wachtwoord = wachtwoord;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getEmailAdres(), user.getEmailAdres()) && Objects.equals(getVoorNaam(), user.getVoorNaam()) && Objects.equals(getAchterNaam(), user.getAchterNaam()) && Objects.equals(getWachtwoord(), user.getWachtwoord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmailAdres(), getVoorNaam(), getAchterNaam(), getWachtwoord());
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailAdres='" + emailAdres + '\'' +
                ", voorNaam='" + voorNaam + '\'' +
                ", achterNaam='" + achterNaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }
}
