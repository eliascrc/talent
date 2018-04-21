package cr.talent.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a System Administrator within the Talent system.
 * It contains the information inherited from {@link cr.talent.model.User} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "system_administrator")
public class SystemAdministrator extends User {

    public SystemAdministrator(){ }

    /**
     * Method that returns the User's authorities, in this case it assigns the SYSTEM_ADMINISTRATOR
     * role along with the ones inherited from its super class .
     * @return the collection of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>(super.getAuthorities());
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_SYSTEM_ADMINISTRATOR"));
        return authorities;
    }
}
