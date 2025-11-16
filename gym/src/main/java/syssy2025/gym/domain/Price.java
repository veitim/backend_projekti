package syssy2025.gym.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="PriceTable")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_id", nullable = false, updatable= false)
    private Long price_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unitprice", nullable = false)
    private double unitprice;

    @JsonIgnoreProperties("price")
    @OneToMany(cascade= CascadeType.ALL, mappedBy= "price")
    private List<Course> Courses;

    public Price() {
    }

    public Price(String name, double unitprice) {
        this.name = name;
        this.unitprice = unitprice;
    }

    public Long getPrice_id() {
        return price_id;
    }

    public void setPrice_id(Long price_id) {
        this.price_id = price_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public List<Course> getCourses() {
        return Courses;
    }

    public void setCourses(List<Course> courses) {
        Courses = courses;
    }

    @Override
    public String toString() {
        return "Price [price_id=" + price_id + ", name=" + name + ", unitprice=" + unitprice + "]";
    }

}
