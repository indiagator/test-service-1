package com.code22.testservice1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kitchen")
public class Kitchen {
    @Id
    @Column(name = "name", nullable = false, length = 80)
    private String id;

    @Column(name = "location", length = 20)
    private String location;

    @Column(name = "menu", length = 640)
    private String menu;

    @Column(name = "reviews", length = 240)
    private String reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

}