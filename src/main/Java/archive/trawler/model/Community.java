package archive.trawler.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Community {
    private static Community community = new Community();

    private static Map<String, User> userMap = new HashMap<>();

    public static Community getCommunity() {
        return community;
    }

    public static void setCommunity(Community community) {
        Community.community = community;
    }

    //    public List<User> getUsers() {
//        return User.getAllUsers();
//    }
//    public List<User> getUsersAsList() {
//        List<User> userslist = getUsers()
//                .stream()
//                .collect(Collectors.toList());
//        return userslist;
//    }
    public static int getCommunitySize() {
        return userMap.size();
    }

    public static String getUserPasswordByEmail(String email) {
        User theUser = userMap.values().stream().filter(user -> user.getEmail() == email).findFirst().orElse(null);
        if (theUser != null) {
            return theUser.getPassword();
        } else {
            return null;
        }
    }


    public static User getUserByName(String naam) {
        return userMap.values().stream().filter(user -> user.getNaam() == naam).findFirst().orElse(null);
    }

    public static User getUserByEmail(String email) {
        return userMap.values().stream().filter(user -> user.getEmail() == email).findFirst().orElse(null);
    }

    public static boolean addUserToMap(User newAccount) {
        System.out.println(newAccount.getEmail());
        if (userMap.values().stream().noneMatch(user -> user.getEmail() == newAccount.getEmail())) { //TODO something going wrong here adds tripple same and no new accounts
            userMap.put(newAccount.getEmail(), newAccount);
            return true;
        } else {
            return false;
        }
    }

    public static Map<String, User> getUserMap() {
        return userMap;
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
     */
    public void addListOfUsersIntoMap(List<User> usersListToMap) {
        for (User user : usersListToMap) {
            if (!userMap.containsValue(user))
                userMap.put("User", user);
        }
    }
}
