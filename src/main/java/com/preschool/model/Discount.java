package com.preschool.model;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discount {

    private String discountName;
    private DiscountType discountType;
    private List<UserType> userType;
    private OrganizationNames organizationName;
    private Map<String, Long > preschoolNamesAndTheirDiscounts;
    private @Id @GeneratedValue int id;


    public Discount() {
    }

    public Discount(String discountName, DiscountType discountType, List<UserType> userType,
                    OrganizationNames organizationName, Map<String, Long> preschoolNamesAndTheirDiscounts) {
        this.discountName = discountName;
        this.discountType = discountType;
        this.userType = userType;
        this.organizationName = organizationName;
        this.preschoolNamesAndTheirDiscounts = preschoolNamesAndTheirDiscounts;
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

    public Map<String, Long> getPreschoolNamesAndTheirDiscounts() {
        return preschoolNamesAndTheirDiscounts;
    }

    public void setPreschoolNamesAndTheirDiscounts(Map<String, Long> preschoolNamesAndTheirDiscounts) {
        this.preschoolNamesAndTheirDiscounts = preschoolNamesAndTheirDiscounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}