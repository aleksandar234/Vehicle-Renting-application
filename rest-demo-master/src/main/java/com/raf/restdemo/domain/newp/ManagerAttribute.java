package com.raf.restdemo.domain.newp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ManagerAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelName;
    private Date employmentDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "managerAttribute")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
