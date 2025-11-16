package syssy2025.gym.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import jakarta.validation.constraints.Size;

@Entity
@Table(name="CourseTable")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false, updatable = false)
    private Long course_id;

    @NotBlank(message = "Course must have a name")
    @Size(max = 100, message = "Max characters 100")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Period needs description")
    @Size(max = 100, message = "Max characters 100")
    @Column(name = "period", nullable = false)
    private String period;

    @JsonIgnoreProperties("courses")
    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;

    @JsonIgnoreProperties("courses")
    @ManyToOne
    @JoinColumn(name = "martialart_id")
    private MartialArt martialart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<CustomerCourse> customercourse;

    public Course() {
    }

    public Course(String name, String period, Price price, MartialArt martialart) {
        this.name = name;
        this.period = period;
        this.price = price;
        this.martialart = martialart;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public MartialArt getMartialart() {
        return martialart;
    }

    public void setMartialart(MartialArt martialart) {
        this.martialart = martialart;
    }

    public List<CustomerCourse> getCustomercourse() {
        return customercourse;
    }

    public void setCustomercourse(List<CustomerCourse> customercourse) {
        this.customercourse = customercourse;
    }

    @Override
    public String toString() {
        return "Course [course_id=" + course_id + ", name=" + name + ", period=" + period + ", price=" + price
                + ", martialart=" + martialart + "]";
    }    

    
   

}
