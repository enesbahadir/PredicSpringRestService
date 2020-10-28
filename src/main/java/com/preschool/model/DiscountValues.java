package com.preschool.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DiscountValues {

    private @Id @GeneratedValue int id;

    @OneToOne
    @JoinColumn(name = "preschool_id")
    private Preschool preschool;

    @ManyToMany(mappedBy = "discountValues")
    private List<Discount> discounts;

    private Long value;

    public DiscountValues(Preschool preschool, Long value) {
        this.preschool = preschool;
        this.value = value;
    }

    public DiscountValues() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
