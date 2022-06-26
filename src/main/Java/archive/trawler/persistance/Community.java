package archive.trawler.persistance;

import archive.trawler.model.Archief;
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
 * Community klas is een behouder van alle informatie, creeert 1 globale community met alle data.
 *
 * @userMap bevat alle Users die een login hebben op de website, en daarbij ook al hun gegevens.
 * Uiteindelijk zal er geen andere klasse opgeslagen hoeven te worden naast deze lijst.
 */
@Data
public class Community implements Serializable {
    private static @Getter
    @Setter Community community = new Community();  // creeert 1 globale community met alle data.

    private static @Getter @Setter Map<String, User> userMap = new HashMap<>();
    private static @Getter @Setter Map<String, Archief> archiefMap = new HashMap<>();
    private static @Getter @Setter Map<String, Archief> zoekOpdrachtMap = new HashMap<>();
    // TODO implement all lists into this file
    //  - [ ] archief
    //  - [ ] websites
    //  - [x] user
    //  - [ ] zoekopdracht / nu opgeslagen in USER, overweeg hier apart op te slaan
    //  - [ ] zoekresultaat
    //  - [ ] check for more



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

        System.out.println("doublechecking user map for: " + newAccount.getEmail());
        System.out.println("... " + newAccount + "");

        if (userMap.values().stream().noneMatch(user -> user.getEmail().equals(newAccount.getEmail()))) {
            userMap.put(newAccount.getEmail(), newAccount);
            return true;
        } else {
            return false;
        }
    }

    /** TODO Combineer addUserToMap en anderen (archiefToMap) etc om dubbele code te vermijden
     * addUserToMap voegt een nieuwe user toe aan de Lijst,
     * maar controleert eerst of deze er nog niet al instaat. maakt hierover ook een log in de logger van deze klasse.
     *
     * @param newAccount een instance van User.
     * @return Een boolean of het het gelukt is om deze gebruiker als unieke User toe te voegen aan de database.
     */
    public static boolean addArchiefToMap(Archief newAccount) {

        System.out.println("doublechecking archief map for: " + newAccount.getNaam());
        System.out.println("... to add: " + newAccount + "");

        if (archiefMap.values().stream().noneMatch(user -> user.getNaam().equals(newAccount.getNaam()))) {
            archiefMap.put(newAccount.getNaam().toLowerCase(), newAccount);
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

    public static Archief getArchiefByName(String naam) {
        return archiefMap.get(naam.toLowerCase());
    }

    public static Archief getArchiefByURI(String uriTeZoeken) {
        return archiefMap.values().stream().filter(archief -> archief.getNaam().equals(uriTeZoeken)).findFirst().orElse(null);
    }
}
