package archive.trawler.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static archive.trawler.model.Community.addUserToMap;

/**
 * Klasse die gebruikt word om de users te creeeren.
 */
public class User implements Serializable {
    private @Getter
    @Setter String naam;
    private @Getter
    @Setter String email;
    private @Getter
    @Setter String role;

    private String password; //plz only store hashed password
    private @Getter
    @Setter Map<Integer, Object> alleZoekertjes;
    private @Getter
    @Setter
    static List<User> allUsers;

    /**
     * @param email email adres, is gelijk ook de username van de inlog
     * @param naam  persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     */
    public User(String naam, String email, String password) {
        this.naam = naam;
        this.email = email;
        this.password = password;
        this.alleZoekertjes = new HashMap<Integer, Object>(10000);
        this.role = "user";
        registerUser(this);
    }


    //Domain endpoint to actually add a MyUser class
    public static boolean registerUser(User user) {
        try {
            return addUserToMap(user);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Zoekt de user die hoort bij dit emailadres
     *
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
            return allUsers.remove(getUserByEmail(email));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * setAllUsers vervangt de huidige lijst allUsers met een aangeleverde List met Users.
     */
    public static void setAllMyUsers(List<User> newAllUsers) {
        allUsers = newAllUsers;
    }

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

    public String getPassword() {
        return password;
    }

    // TODO schrijf functie addZoekertjeAanAlleZoekrtjes
    //  scrhijf een functie die het mogelijk maakt een zoekopdracht op te slaan in de lijst
    //  labels: User, zoekopdracht opslaan
}
