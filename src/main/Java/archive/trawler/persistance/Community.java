package archive.trawler.persistance;

import archive.trawler.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class Community {
    private static @Getter
    @Setter Community community = new Community();

    private static @Getter
    @Setter Map<String, User> userMap = new HashMap<>();

    public static User getUserByEmail(String email) {
        return userMap.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public static boolean addUserToMap(User newAccount) {

        Logger logger = Logger.getLogger(Community.class.getName());
        logger.log(Level.FINE, "doublechecking user map for: "+newAccount.getEmail());
        System.out.println("doublechecking user map for: "+newAccount.getEmail());
        System.out.println("... "+newAccount.toString()+"");

        if (userMap.values().stream().noneMatch(user -> user.getEmail().equals(newAccount.getEmail()))) { //TODO something going wrong here adds tripple same and no new accounts
            userMap.put(newAccount.getEmail(), newAccount);
            return true;
        } else {
            return false;
        }
    }

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
    public void addListOfUsersIntoMap(List<User> usersListToMap) {
        for (User user : usersListToMap) {
            if (!userMap.containsValue(user)) userMap.put("User", user);
        }
    }
}
