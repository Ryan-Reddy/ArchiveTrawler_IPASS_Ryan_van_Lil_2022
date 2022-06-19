package archive.trawler.security;


import archive.trawler.model.User;
import lombok.Getter;
import lombok.Setter;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/** Klasse die gebruikt wordt voor het opslaan van gevoelige inlogdata */
public class MyUser implements Principal {
    private @Getter @Setter String email;  //
    private String password; //plz only store hashed password
    private @Getter String role;
    private @Getter User user;

    /**
     * Klasse die gebruikt wordt voor het opslaan van gevoelige inlogdata.
     * @param email  email adres, is gelijk ook de link met de User van de opslag van data
     * @param password ww wordt gebruikt voor inloggen
     */

    private MyUser(String email, String password) {
        if (email == null
                || password == null
                || Stream.of(email, password).anyMatch(String::isBlank)) {
            throw new IllegalArgumentException();}
        this.email = email;
        this.password = password;
        this.role = "user";
        allMyUsers.add(this);
    }

    public String getRole() {
        return role;
    }

    private static List<MyUser> allMyUsers = new ArrayList<>();

    public static String validateLogin(String email, String password) {
        MyUser toLogin = getMyUserByEmail(email);
        if(toLogin!=null && toLogin.password.equals(password)) {
            return toLogin.getRole();
        }
        return null;
    }

    public static MyUser getMyUserByEmail(String emailAddr) {
        return allMyUsers.stream()
                .filter(user -> user.email.equals(emailAddr))
                .findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUser myUser = (MyUser) o;
        return email.equals(myUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    //Domain endpoint to actually add a MyUser class
    public static boolean registerUser(String email, String password, String naam){
        if(!allMyUsers.contains(MyUser.getMyUserByEmail(email))
                && password.length() >= 6
        ) {
            new User(email, naam);
            MyUser newUser;
            try {
                newUser = new MyUser(email, password);
                return addUser(newUser);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } return false;
    }

    /** This will add the user to the static list allMyUsers */
    private static boolean addUser(MyUser toAdd) {
        if (!allMyUsers.contains(toAdd)) {
            return allMyUsers.add(toAdd);
        }
        return false;
    }

    /** Will return username -> in this context it is the email*/
    @Override
    public String getName() {
        return email;
    }

    /** WARNING cannot be undone...
     * This method will remove this user from the database. They will not be able to login
     * seperate from the User account, this data will be stored indefinately
     * */
    public static boolean deleteMyUserAccount(String email) {
                try { return allMyUsers.remove(getMyUserByEmail(email));}
                catch (Exception e) {
                    return false;
                }

    }
}
