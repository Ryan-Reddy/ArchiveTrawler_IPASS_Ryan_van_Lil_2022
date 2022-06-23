package archive.trawler.security.dto;

import archive.trawler.model.User;
import archive.trawler.security.MyUser;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/** MySecurityContext
 *
 */
public class MySecurityContext implements SecurityContext {
    private final User user;
    private final String scheme;
    public MySecurityContext(User user, String scheme) {
        this.user = user;
        this.scheme = scheme;
    }
    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }
    @Override
    public boolean isUserInRole(String s) {
        if (user.getRole() != null) {
            System.out.printf("%s equals %s", s, user.getRole());
            return s.equals(user.getRole());
        }
        return false;
    }
    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }
    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}