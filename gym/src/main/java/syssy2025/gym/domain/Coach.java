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

@Entity
@Table(name="CoachTable")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coach_id", nullable = false, updatable = false)
    private Long coach_id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

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
