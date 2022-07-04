package archive.trawler.webservices.dto;

/** Een DTO met nieuw ww voor deze gebruiker.
 * @Attribute password; wachtwoord
 * @Attribute password2; nogmaals hetzelfde wachtwoord ter verificatie.
 */
public class PasswordReset {
    public String password;
    public String password2;
}
