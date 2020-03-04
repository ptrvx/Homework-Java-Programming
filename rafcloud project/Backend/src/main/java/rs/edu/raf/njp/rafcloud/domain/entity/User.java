package rs.edu.raf.njp.rafcloud.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.HashSet;

public class User extends org.springframework.security.core.userdetails.User {

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Role role;

    private String token;

    public User() {
        this("anonymousUser", "anonymousUser", Role.ROLE_ANONYMOUS);
    }

    public User(String username, String password, Role role) {
        super(username, password, AuthorityUtils.createAuthorityList(role.toString()));
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, Role role, String token) {
        this(username, password, role);
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
