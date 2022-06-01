package main.Java.model;

import java.util.HashMap;
import java.util.Map;

public class Community {
    private static Community community = new Community();

    private Map<String, User> users = new HashMap<>();

    public static Community getCompany() {
        return community;
    }

    public User getAccount(String username) {
        return users.get(username);
    }

    public boolean addAccount(User newAccount) {
        if (newAccount.getUserName().isEmpty() || users.containsKey(newAccount.getUserName())) {
            return false;
        } else {
            users.put(newAccount.getUserName(), newAccount);
            return true;
        }
    }
}
