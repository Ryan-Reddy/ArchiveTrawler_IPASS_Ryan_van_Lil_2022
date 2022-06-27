package archive.trawler.security;


import archive.trawler.model.Zoekopdracht;
import archive.trawler.model.User;
import io.jsonwebtoken.JwtException;
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

    private static final @Getter List<MyUser> allMyUsers = new ArrayList<>();

    /** Klasse die gebruikt wordt voor het opslaan van gevoelige inlogdata.
     * @param naam              persoonlijke naam, voor en achternaam wil geen assumpties maken over opbouw
     * @param email             email adres, is gelijk ook de username van de inlog
     * @param role              Rol van de gebruiker, zal doorgaans user zijn
     * @param password          ww wordt gebruikt voor inloggen
     * @param alleZoekertjes    alle zoekopdrachten van de gebruiker
     */
    public MyUser(String naam, String email, String role, String password, List<Zoekopdracht> alleZoekertjes) {
        super(naam, email, password);
    }

    /**
     * Hiermee authenticeer je een gebruiker, door middel van gebruik van de email en wachtwoord
     * @param email
     * @param password
     * @return rol van de gebvruiker als string
     * @return if not valid, returns null
     */
    public static String validateLogin(String email, String password) {
        User toLogin = getUserByEmail(email);
        if (toLogin != null && toLogin.getPassword().equals(password)) {
            System.out.println("validateLogin(): user password matches");
            String role =  toLogin.getRole();
            System.out.println("role that matches this validated login: " + role);
            return role;
        }
        return null;
    }

}
