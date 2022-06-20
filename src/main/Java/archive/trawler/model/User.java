package archive.trawler.model;

import archive.trawler.security.MyUser;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Klasse die gebruikt word om de users te creeeren. */
public class User implements NamedObject, Serializable {
    private String naam;
    private String email;
    private String role;
    private List<AlleZoekopdracht> alleZoekertjes;
    private static List<User> allUsers = new ArrayList<>();

    private @Getter User user;

    /**
     * @param email email adres, is gelijk ook de username van de inlog
     * @param naam persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     */
    public User(String email, String naam) {
//        if (getUserByEmail(email)==null) {
            this.email = email;
            this.naam = naam;
            this.role = "user";
            addUserToList(this);
//        }
    }

    public static void setAllUsers(List<User> allUsers) {
        User.allUsers = allUsers;
    }

    public void addUserToList(User userToList){
        allUsers.add(userToList);
    }

    public String getRole() { return role; }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    /** Zoekt de user die hoort bij dit emailadres
     * @return de User  */
    public static User getUserByEmail(String email) {
        return allUsers.stream()
                .filter(user -> user.email.equals(email))
                .findFirst().orElse(null);
    }

    /** kan gebruikt worden om de naam van deze gebruiker te wijzigen */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /** Zoekt alle zoekopdrachten van deze User
     * @return lijst met alleZoekopdrachten */
    public List<AlleZoekopdracht> getAlleZoekertjes() {
        return alleZoekertjes;
    }

    /** returned de naam van deze User, dit bevat meestal voor en achternaam
     * @return String met de naam*/
    @Override
    public String getName() {
        return naam;
    }

    /** returned emailadres van deze User
     * @return String van het email adres*/
    public String getEmailAdres() {
        return email;
    }
    /** kan gebruikt worden om een email adres bij dit account te wijzigen
     * @return boolean of het gelukt is */
    public boolean setEmail(String newEmail) {
        try {
            MyUser.getMyUserByEmail(email).setEmail(newEmail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // TODO schrijf functie addZoekertjeAanAlleZoekrtjes
    //  scrhijf een functie die het mogelijk maakt een zoekopdracht op te slaan in de lijst
    //  labels: User, zoekopdracht opslaan
}
