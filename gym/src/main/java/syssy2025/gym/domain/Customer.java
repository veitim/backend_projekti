package syssy2025.gym.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "CustomerTable")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long customer_id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Email(message = "Bad email address")
    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;
    
    @ManyToOne
    @JsonIgnoreProperties("customers")
    @JoinColumn(name = "customercourse_id")
    private CustomerCourse customercourse;

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

    public CustomerCourse getCustomercourse() {
        return customercourse;
    }

    public void setCustomercourse(CustomerCourse customercourse) {
        this.customercourse = customercourse;
    }

    @Override
    public String toString() {
        if (this.customercourse != null)
            return "Customer [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
                    + ", email=" + email + ", address=" + address + " customercourse =" + this.getCustomercourse() + "]";
        else
            return "Customer [customer_id=" + customer_id + ", firstname=" + firstname + ", lastname=" + lastname
                    + ", email=" + email + ", address=" + address + "]";
    }

}
