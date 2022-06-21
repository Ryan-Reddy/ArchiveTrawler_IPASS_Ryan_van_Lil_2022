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
    private @Getter
    @Setter String naam;
    private @Getter
    @Setter String email;
    private @Getter
    @Setter String role;

    private final String password; //plz only store hashed password
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
