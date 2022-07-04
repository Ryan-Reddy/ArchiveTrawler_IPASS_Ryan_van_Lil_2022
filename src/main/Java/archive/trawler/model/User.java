package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse die gebruikt word om de users te creeeren.
 */
@Data
public class User implements Serializable, Principal {
    /** Uniek ID nummer van gebruiker*/
    private @Getter @Setter int ID;
    /**wachtwoord van de gebruiker.*/
    private @Getter @Setter String password; //plz only store hashed password
    /**naam van de gebruiker, persoonlijke naam, voor en/of achternaam. */
    private @Getter @Setter String naam;
    /** email adres, is gelijk ook de username van de inlog */
    private @Getter @Setter String email;
    /**rol van de gebruiker.     */
    private @Getter @Setter String role;

    /** Alle zoekopdrachten van deze gebruiker, in de vorm van een genummerde map.  */
    private @Getter
    @Setter ArrayList<Zoekopdracht> alleZoekertjes = new ArrayList<>();



    /** Lege constructor voor User.
     */
    public User() {
        this.role = "user";
        this.naam = "";
    }

//    /**
//     * @param email email adres, is gelijk ook de username van de inlog
//     * @param naam  persoonlijke naam, voor en/of achternaam
//     * @param password ww
//     */
//    public User(String naam, String email, String password) {
//        this.naam = naam;
//        this.email = email;
//        this.password = password;
//        this.role = "user";
//        Community.addUserToMap(this);
//    }

    /**
     * @param email email adres, is gelijk ook de username van de inlog
     * @param naam  persoonlijke naam, voor en/of achternaam
     * @param password ww
     */
    public User(String naam, String email, String password) {
        this.naam = naam;
        this.email = email;
        this.password = password;
        this.role = "user";
        Community.addUserToMap(this);
    }

    private int randomIDgenerator() {
        int max = 10000;
        int min = 999;
        long epochSecond = Instant.now().getEpochSecond(); //Long = 1450879900

        return (int) (epochSecond + (min + (Math.random() * max))); // TODO doe iets met de uniqueID danwel in user, danwel in de zoekkant van website
    }

    /**
     * Zoekt de user die hoort bij dit emailadres
     *
     * @param email de email die bij de gebruiker hoort, is meteen ook de key om deze op te zoeken.
     * @return de User
     */
    public static User getUserByEmail(String email) {
        return Community.getUserByEmail(email);
    }

    /**
     * WARNING cannot be undone...
     * This method will remove this user from the database. They will not be able to login
     * seperate from the User account, this data will be stored indefinately
     */
    public static boolean deleteMyUserAccount(String email) {
        try {
            return Community.deleteMyUserAccount(email);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Vervangt de huidige lijst allUsers met een aangeleverde List met Users.
     */
    public static void setAllMyUsers(List<User> newAllUsers) {
        Community.setListOfUsersIntoMap(newAllUsers);
    }

    /**
     * Voegt een zoekopdracht toe aan de lijst bewaarde zoekopdrachten die bij deze user hoort.
     */
    public boolean addZoekertjeAanAlleZoekertjes(Zoekopdracht zoekopdracht) {
        try {
            alleZoekertjes.add(zoekopdracht);
            return true;
        } catch (Exception e) {
            System.out.println("Je kan niet meer dan 10000 zoekopdrachten hebben");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Haalt de naam op van deze User
     * @return String naam;
     */
    @Override
    public String getName() {
        return naam;
    }

    /**
     * Security Context methode.
     * @param subject the {@code Subject}
     * @return boolean true or no.
     */
    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
