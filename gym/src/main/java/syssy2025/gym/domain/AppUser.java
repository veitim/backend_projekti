package syssy2025.gym.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UserTable")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appuser_id", nullable = false, updatable = false)
    private Long appuser_id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String passwordHash;

    @JsonIgnore
    @Column(name = "role", nullable = false)
    private String role;
    
    public AppUser() {
    }

    public AppUser(String firstname, String lastname, String username, String passwordHash, String role) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAppuser_id(Long appuser_id) {
        this.appuser_id = appuser_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAppuser_id() {
		return appuser_id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    @Override
    public String toString() {
        return "AppUser [appuser_id=" + appuser_id + ", firstname=" + firstname + ", lastname=" + lastname + ", passwordHash="
                + passwordHash + ", role=" + role + "]";
    }

}