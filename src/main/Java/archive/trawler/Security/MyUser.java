package archive.trawler.Security;


import archive.trawler.model.Community;
import archive.trawler.model.User;
import lombok.Getter;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class MyUser implements Principal {
    private String username;
    private String password; //plz only store hashed password
    private String role;
    private @Getter User user;

    private MyUser(String username, String password, User theUser) {
        if (username == null || password == null || Stream.of(username, password).anyMatch(String::isBlank))
            throw new IllegalArgumentException();
        this.username = username;
        this.password = password;
        this.role = "user";
        this.user = theUser;
        allUsers.add(this);
    }

    public String getRole() {
        return role;
    }

    private static List<MyUser> allUsers = new ArrayList<>();

    public static String validateLogin(String username, String password) {
        MyUser toLogin = getUserByName(username);
        if(toLogin!=null && toLogin.password.equals(password)) {
            return toLogin.getRole();
        }
        return null;
    }

    public static MyUser getUserByName(String name) {
        return allUsers.stream().filter(user -> user.username.equals(name)).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return username.equals(myUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    //Domain endpoint to actually add a MyUser class
    public static boolean registerUser(String username, String password){
        MyUser newUser;
        try{
            newUser = new MyUser(username, password, Community.getUserByName(username));
            return addUser(newUser);
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private static boolean addUser(MyUser toAdd) {
        if (!allUsers.contains(toAdd)) return allUsers.add(toAdd);
        return false;
    }

    @Override
    public String getName() {
        return username;
    }
}
