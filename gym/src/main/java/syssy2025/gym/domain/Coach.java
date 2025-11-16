package syssy2025.gym.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="CoachTable")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coach_id", nullable = false, updatable = false)
    private Long coach_id;

    @NotBlank(message = "Coach must have first name")
    @Size(min = 2, max = 50, message = "First name have to be 2-50 characters long")
    @Column(name = "firstname", nullable = false, length=50)
    private String firstname;

    @NotBlank(message = "Coach must have last name")
    @Size(min = 2, max = 50, message = "Last name have to be 2-50 characters long")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coach")
    @JsonBackReference
    private List<MartialArt> martialart;

    public Coach() {
    }

    public Coach(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(Long coach_id) {
        this.coach_id = coach_id;
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

    public List<MartialArt> getMartialart() {
        return martialart;
    }

    public void setMartialart(List<MartialArt> martialart) {
        this.martialart = martialart;
    }

    @Override
    public String toString() {
        return "Coach [coach_id=" + coach_id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }
    
}
