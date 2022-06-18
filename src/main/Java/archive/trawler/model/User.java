package archive.trawler.model;

import archive.trawler.Security.MyUser;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/** Klasse die gebruikt word om de users te creeeren. */
public class User implements NamedObject {
    private String naam;
    private String email;
    private String role;
    private List<AlleZoekopdracht> alleZoekertjes;
    private static List<User> allUsers = new ArrayList<>();

    private @Getter User user;

    /**
     * @param emailAdres email adres, is gelijk ook de username van de inlog
     * @param naam persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     */

    public User(String emailAdres, String naam) {
        if (getUserByEmail(emailAdres)==null) {
            this.email = emailAdres;
            this.naam = naam;
            this.role = "user";
            allUsers.add(this);
        }

    }

    public String getRole() { return role; }

    public List<User> getAllUsers() {
        return allUsers;
    }

    /** Zoekt de user die hoort bij dit emailadres
     * @return de User  */
    public User getUserByEmail(String email) {
        return allUsers.stream()
                .filter(user -> user.email.equals(email))
                .findFirst().orElse(null);
    }

/** geeft de naam van deze gebruiker
 * @return String met de naam */
    public String getNaam() {
        return naam;
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
            this.email = email;
            MyUser.getMyUserByEmail(email).setEmail(email);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
