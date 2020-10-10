package com.preschool.repository;

import com.preschool.model.Preschool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPreschoolRepository extends JpaRepository <Preschool, Integer>{
}
