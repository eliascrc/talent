package cr.talent.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a User within the Talent system.
 * It contains the username, password, first name, last name and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@MappedSuperclass
public abstract class User extends BasicEntity implements UserDetails {

    public enum Status {ACTIVE, INACTIVE, SUSPENDED}

    /**
     * Username can't be empty, null or duplicated. It represents the user's email address.
     */
    @Column(name = "username", nullable = false, unique = true)
    protected String username;

    /**
     * The user's first name.
     */
    @Column(name = "first_name", nullable = false)
    protected String firstName;

    /**
     * The user's last name.
     */
    @Column(name = "last_name", nullable = false)
    protected String lastName;

    /**
     * User password.
     */
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * Flag to indicate if the user name has been enabled or disabled.
     */
    @Column(name = "enabled", nullable = false)
    protected boolean enabled;

    /**
     * Flag to indicate if the account needs a change in the password.
     */
    @Column(name = "password_needs_change", nullable = false)
    protected boolean passwordNeedsChange;

    /**
     * Timestamp of the last login of the user.
     */
    @Column(name = "last_login_timestamp")
    private Date lastLoginTimestamp;

    /**
     * The status of the user account.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public User (){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof User){
            User user = (User) o;
            result = (this.username == null ? user.getUsername() == null : this.username.equals(user.getUsername())
                    && this.password == null ? user.getPassword() == null : this.password.equals(user.getPassword()));
        }
        return result;
    }

    /**
     * Method that returns the User's authorities, in this case it assigns the USER role,
     * which both users Technical Resource and System Administrator have.
     * @return the collection of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>();
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + ((this.username == null)? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null)? 0 : this.password.hashCode());
        return result;
    }

    /**
     * @author Rodrigo Bartels
     * @return StringBuilder
     */
    protected StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append(", username = ").append(this.getUsername());
        sb.append(", enabled = ").append(this.isEnabled());
        sb.append(", passwordNeedChange = ").append(this.getPasswordNeedsChange());
        return sb;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getPasswordNeedsChange() {
        return passwordNeedsChange;
    }

    public void setPasswordNeedsChange(boolean passwordNeedsChange) {
        this.passwordNeedsChange = passwordNeedsChange;
    }

    public Date getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Date lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}