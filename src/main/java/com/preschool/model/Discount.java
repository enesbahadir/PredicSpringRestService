package com.preschool.model;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Discount {

    private String discountName;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Enumerated(EnumType.STRING)
    private List<UserType> userType;

    private OrganizationNames organizationName;

    @OneToMany
    private Set<DiscountsOfPreschool> discountsOfPreschool;

    private @Id @GeneratedValue int id;

    public Discount() { }

    public Discount(String discountName, DiscountType discountType, List<UserType> userType,
                    OrganizationNames organizationName, Set<DiscountsOfPreschool> discountsOfPreschool, int id) {
        this.discountName = discountName;
        this.discountType = discountType;
        this.userType = userType;
        this.organizationName = organizationName;
        this.discountsOfPreschool = discountsOfPreschool;
        this.id = id;
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

    public List<UserType> getTypeOfUser() {
        return userType;
    }

    public void setTypeOfUser(List<UserType> userType) {
        this.userType = userType;
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
}