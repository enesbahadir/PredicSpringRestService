package com.preschool.model;

import javax.persistence.*;

@Entity
public class DiscountsOfPreschool {

    private @Id @GeneratedValue int id;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "preschool_id")
    private Preschool preschool;

    private Long value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Preschool getPreschool() {
        return preschool;
    }

    public void setPreschool(Preschool preschool) {
        this.preschool = preschool;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
