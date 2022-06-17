package archive.trawler.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Community {
    private static Community community = new Community();

    private static Map<String, User> users = new HashMap<>();

    public static Community getCommunity() {
        return community;
    }

    public Map<String, User> getUsers() {
        return users;
    }
    public List<User> getUsersAsList() {
        List<User> userslist = getUsers()
                .values()
                .stream()
                .collect(Collectors.toList());
        return userslist;
    }

    public static int getCommunitySize() {
        List l = (List) users;
        return l.size();
    }

    public static User getUserByName(String username) {
        return users.get(username);
    }

    public boolean addAccount(User newAccount) {
        if (newAccount.getEmailAdres().isEmpty() || users.containsKey(newAccount.getEmailAdres())) {
            return false;
        } else {
            users.put(newAccount.getEmailAdres(), newAccount);
            return true;
        }
    }

    public static void setCommunity(Community community) {
        Community.community = community;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
