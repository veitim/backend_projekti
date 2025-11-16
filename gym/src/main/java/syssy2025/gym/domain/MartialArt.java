package syssy2025.gym.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "MartialArtTable")
public class MartialArt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "martialart_id", nullable = false, updatable = false)
    private Long martialart_id;

    @NotBlank(message = "Martial Art needs a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Martial Art needs a head coach")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "martialart")
    private List<Course> courses;

    public MartialArt() {
    }

    public MartialArt(String name, Coach coach) {
        super();
        this.name = name;
        this.coach = coach;
    }

    public Long getMartialart_id() {
        return martialart_id;
    }

    public void setMartialart_id(Long martialart_id) {
        this.martialart_id = martialart_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        if (this.coach != null)
            return "MartialArt [martialart_id=" + martialart_id + ", name=" + name + ", coach=" + this.getCoach() + ", courses="
                + courses + "]";
        else
            return "MartialArt [martialart_id=" + martialart_id + ", name=" + name + ", courses="
                + courses + "]";
    }

}

