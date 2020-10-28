package com.preschool.model;

import com.preschool.enums.UserType;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

@Entity
public class Discount {

    private String discountName;
    private String discountType;


    @ElementCollection(targetClass=UserType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="discount_userType")
    @Column(name="userType")
    private List<UserType> userType;

    private String organizationName;

    //@ManyToMany(targetEntity = DiscountValues.class,cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "discountValues_discount",
            joinColumns = @JoinColumn(name = "discountValues_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id",
                    referencedColumnName = "id"))
    @RestResource(path = "values", rel="values")
    private List<DiscountValues> discountValues;

    public Discount(String discountName, String discountType, List<UserType> userType,
                    String organizationName) {
        this.discountName = discountName;
        this.discountType = discountType;
        this.userType = userType;
        this.organizationName = organizationName;
    }

    private @Id @GeneratedValue int id;

    public Discount() { }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public List<UserType> getUserType() {
        return userType;
    }

    public void setUserType(List<UserType> userType) {
        this.userType = userType;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<DiscountValues> getDiscountValues() {
        return discountValues;
    }

    public void setDiscountValues(List<DiscountValues> discountValues) {
        this.discountValues = discountValues;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}