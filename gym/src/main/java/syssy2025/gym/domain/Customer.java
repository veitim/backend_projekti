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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "CustomerTable")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long customer_id;

    @NotBlank
    @Size(min=3, max=30)
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Size(min=3, max=30)
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotBlank
    @Size(min=3, max=50)
    @Email(message = "Bad email address")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min=3, max=50)
    @Column(name = "address")
    private String address;
    
    @JsonIgnoreProperties("customers")
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerCourse> customercourse;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CustomerCourse> getCustomercourse() {
        return customercourse;
    }

    public void setCustomercourse(List<CustomerCourse> customercourse) {
        this.customercourse = customercourse;
    }

    @Override
    public String toString() {
            return "Customer [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
                    + ", email=" + email + ", address=" + address +  "]";
    }

}
