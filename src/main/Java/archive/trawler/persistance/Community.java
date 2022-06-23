package archive.trawler.persistance;

import archive.trawler.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Community klas is een behouder van alle informatie
 *
 * @userMap bevat alle Users die een login hebben op de website, en daarbij ook al hun gegevens.
 * Uiteindelijk zal er geen andere klasse opgeslagen hoeven te worden naast deze lijst.
 */
@Data
public class Community implements Serializable {
    private static @Getter
    @Setter Community community = new Community();

    private static @Getter
    @Setter Map<String, User> userMap = new HashMap<>();

    public static User getUserByEmail(String email) {
        return userMap.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    /**
     * addUserToMap voegt een nieuwe user toe aan de Lijst,
     * maar controleert eerst of deze er nog niet al instaat. maakt hierover ook een log in de logger van deze klasse.
     *
     * @param newAccount een instance van User.
     * @return Een boolean of het het gelukt is om deze gebruiker als unieke User toe te voegen aan de database.
     */
    public static boolean addUserToMap(User newAccount) {

        Logger logger = Logger.getLogger(Community.class.getName());
        logger.log(Level.FINE, "doublechecking user map for: " + newAccount.getEmail());
        System.out.println("doublechecking user map for: " + newAccount.getEmail());
        System.out.println("... " + newAccount + "");

        if (userMap.values().stream().noneMatch(user -> user.getEmail().equals(newAccount.getEmail()))) {
            userMap.put(newAccount.getEmail(), newAccount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * deleteMyUserAccount
     * Doet precies wat je verwacht, verwijderd een gebruiker uit de lijst.
     * Deze gebruiker zal bljven bestaan terwijl de server draait, maar zal daarna neet meer te achterhalen zijn.
     * Ook kan deze vanaf dit moment niet meer zelf inloggen.
     *
     * @param email email van de gebruiker die gekoppeld is aan de User account.
     * @return Een boolean of het gelukt is of niet.
     */
    public static boolean deleteMyUserAccount(String email) {
        try {
            userMap.remove(email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * setListOfUsersIntoMap
     * Is een functie die een lijst met users aanneemt en deze opslaat als Map.
     * Zou gebruikt kunnen worden voor persistentie
     */
    public static void setListOfUsersIntoMap(List<User> usersListToMap) {
        for (User user : usersListToMap) {
            if (!userMap.containsValue(user)) userMap.put("User", user);
        }
    }
}
