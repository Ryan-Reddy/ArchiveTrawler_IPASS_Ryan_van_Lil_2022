package archive.trawler.model;

import archive.trawler.persistance.Community;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasse die gebruikt word om de users te creeeren.
 */
public class User implements Serializable {
    /** naam van de gebruiker, persoonlijke naam, voor en/of achternaam. */
    private @Getter
    @Setter String naam;
    /** email adres, is gelijk ook de username van de inlog */
    private @Getter
    @Setter String email;
    /** rol van de gebruiker. */
    private @Getter @Setter String role;
    /** wachtwoord van de gebruiker. */
    private final String password; //plz only store hashed password
    /** Alle zoekopdrachten van deze gebruiker, in de vorm van een genummerde map.*/
    private @Getter
    @Setter Map<Integer, Object> alleZoekertjes;
//    private @Getter
//    @Setter
//    static List<User> allUsers;

    /**
     * @param email email adres, is gelijk ook de username van de inlog
     * @param naam  persoonlijke naam, voor en/of achternaam
     */
    public User(String naam, String email, String password) {
        this.naam = naam;
        this.email = email;
        this.password = password;
        this.alleZoekertjes = new HashMap<Integer, Object>(10000);
        this.role = "user";
        Community.addUserToMap(this);
    }


//    //Domain endpoint to actually add a MyUser class
//    public static boolean registerUser(User user) {
//        try {
//            return addUserToMap(user);
//        } catch (Exception e) {
//            return false;
//        }
//    }


    /**
     * Zoekt de user die hoort bij dit emailadres
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

    /** Vervangt de huidige lijst allUsers met een aangeleverde List met Users. */
    public static void setAllMyUsers(List<User> newAllUsers) {
        Community.setListOfUsersIntoMap(newAllUsers);
    }

    /** Voegt een zoekopdracht toe aan de lijst bewaarde zoekopdrachten die bij deze user hoort. */
    public boolean addZoekertjeAanAlleZoekertjes(Zoekopdracht zoekopdracht) {
        try {
            alleZoekertjes.put((alleZoekertjes.size() + 1), zoekopdracht);
            return true;
        } catch (Exception e) {
            System.out.println("Je kan niet meer dan 10000 zoekopdrachten hebben");
            e.printStackTrace();
            return false;
        }
    }

    /** Deze functie haalt het ww op van deze gebruiker.
     * TODO security implementeren getPassword */
    public String getPassword() {
        return password;
    }
}
