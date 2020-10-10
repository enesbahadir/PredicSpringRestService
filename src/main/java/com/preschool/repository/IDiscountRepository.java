package com.preschool.repository;

import com.preschool.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiscountRepository extends JpaRepository<Discount, Integer> {
}
