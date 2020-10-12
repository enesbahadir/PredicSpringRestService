package com.preschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Preschool {
    private String preschoolName;
    private long price;
    private String 	endOfEarlyRegistrationDate;
    private @Id @GeneratedValue int id;

    public Preschool() { }

    public Preschool(String preschoolName,
                     String endOfEarlyRegistrationDate, long price, int id) {
        this.preschoolName = preschoolName;
        this.price = price;
        this.endOfEarlyRegistrationDate = endOfEarlyRegistrationDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPreschoolName() {
        return preschoolName;
    }

    public void setPreschoolName(String preschoolName) {
        this.preschoolName = preschoolName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getEndOfEarlyRegistrationDate() {
        return endOfEarlyRegistrationDate;
    }

    public void setEndOfEarlyRegistrationDate(String endOfEarlyRegistrationDate) {
        this.endOfEarlyRegistrationDate = endOfEarlyRegistrationDate;
    }
}
