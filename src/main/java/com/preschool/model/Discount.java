package com.preschool.model;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;

import java.util.List;

import javax.persistence.*;

@Entity
public class Discount {

    private String discountName;
    private DiscountType discountType;

    @ElementCollection(targetClass=UserType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="discount_userType")
    @Column(name="userType")
    private List<UserType> userType;

    private OrganizationNames organizationName;

    @ManyToMany(targetEntity = DiscountsOfPreschool.class,cascade = CascadeType.ALL)
    private List<DiscountsOfPreschool> discountsOfPreschool;

    public List<DiscountsOfPreschool> getDiscountsOfPreschool() {
        return discountsOfPreschool;
    }

    public void setDiscountsOfPreschool(List<DiscountsOfPreschool> discountsOfPreschool) {
        this.discountsOfPreschool = discountsOfPreschool;
    }

    private @Id @GeneratedValue int id;

    public Discount() { }

    public Discount(String discountName, DiscountType discountType, List<UserType> userType,
                    OrganizationNames organizationName) {
        this.discountName = discountName;
        this.discountType = discountType;
        this.userType = userType;
        this.organizationName = organizationName;

    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public OrganizationNames getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(OrganizationNames organizationName) {
        this.organizationName = organizationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserType> getUserType() {
        return userType;
    }

    public void setUserType(List<UserType> userType) {
        this.userType = userType;
    }

}