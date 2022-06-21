package archive.trawler.security;


import archive.trawler.model.Zoekopdracht;
import archive.trawler.model.User;
import lombok.Getter;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


/** Klasse die gebruikt wordt voor het opslaan van gevoelige inlogdata */
public abstract class MyUser extends User implements Principal, Serializable {
//    private @Getter @Setter String email;  //
//    private String password; //plz only store hashed password
//    private @Getter String role;
//    private @Getter User user;

    private static @Getter List<MyUser> allMyUsers = new ArrayList<>();

    /**
     * @param naam           persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     * @param email          email adres, is gelijk ook de username van de inlog
     * @param role
     * @param password
     * @param alleZoekertjes
     */
    public MyUser(String naam, String email, String role, String password, List<Zoekopdracht> alleZoekertjes) {
        super(naam, email, password);
    }

    /**
     * @param email email adres, is gelijk ook de username van de inlog
     * @param naam  persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     */


    /**
     * Klasse die gebruikt wordt voor het opslaan van gevoelige inlogdata.
     * @param email  email adres, is gelijk ook de link met de User van de opslag van data
     * @param password ww wordt gebruikt voor inloggen
     */

//    private MyUser(String email, String password) {
//        if (email == null
//                || password == null
//                || Stream.of(email, password).anyMatch(String::isBlank)) {
//            throw new IllegalArgumentException();}
//        this.email = email;
//        this.password = password;
//        this.role = "user";
//    }

    public static String validateLogin(String email, String password) {
        User toLogin = getUserByEmail(email);
        if(toLogin!=null && toLogin.getPassword().equals(password)) {
            return toLogin.getRole();
        }
        return null;
    }


    /** This will add the user to the static list allMyUsers */
    private static boolean addUser(MyUser toAdd) {
        if (!allMyUsers.contains(toAdd)) {
            return allMyUsers.add(toAdd);
        }
        return false;
    }
}
