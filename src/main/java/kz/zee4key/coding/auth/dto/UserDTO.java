package kz.zee4key.coding.auth.dto;

import java.util.Set;
import kz.zee4key.coding.auth.model.Role;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}