package archive.trawler.webservices.dto;

/** Een DTO met de info om een nieuw account aan te maken.
 * @Attribute naam; naam van de gebruiker
 * @Attribute email; email; van de nieuwe gebruiker
 * @Attribute password; wachtwoord; van de nieuwe gebruiker
 */
public class NewAccount {
    public String naam;
    public String email;
    public String password;
}
