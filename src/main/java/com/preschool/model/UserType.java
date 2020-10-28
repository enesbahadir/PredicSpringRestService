package com.preschool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserType {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String userType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public UserType(String userType) {
        this.userType = userType;
    }

    public UserType() {
    }
}
