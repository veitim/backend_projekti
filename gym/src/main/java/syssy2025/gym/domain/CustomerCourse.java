package syssy2025.gym.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CustomerCourseTable")
public class CustomerCourse  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customercourse_id", nullable = false, updatable = false)
    private Long customercourse_id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "paidstatus", nullable = false)
    private boolean paidstatus;

    public CustomerCourse() {
    }

    public CustomerCourse(Customer customer, Course course, boolean paidstatus) {
        super();
        this.customer = customer;
        this.course = course;
        this.paidstatus = paidstatus;
    }

    public Long getCustomercourse_id() {
        return customercourse_id;
    }

    public void setCustomercourse_id(Long customercourse_id) {
        this.customercourse_id = customercourse_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isPaidstatus() {
        return paidstatus;
    }

    public void setPaidstatus(boolean paidstatus) {
        this.paidstatus = paidstatus;
    }

    @Override
    public String toString() {
        return "CustomerCourse [customercourse_id=" + customercourse_id + ", customer=" + this.getCustomer() + ", course="
                + this.getCourse() + ", paidstatus=" + paidstatus + "]";
    }

}
