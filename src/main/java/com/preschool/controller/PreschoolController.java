package com.preschool.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import com.preschool.model.Preschool;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.repository.PreschoolRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PreschoolController {

    private final PreschoolRepository preschoolRepository;

    public PreschoolController(PreschoolRepository preschoolRepository) {
        this.preschoolRepository = preschoolRepository;
    }

    @GetMapping("/preschools")
    CollectionModel<EntityModel<Preschool>> listOfPreschools()
    {
        List<EntityModel<Preschool>> preschools = preschoolRepository.findAll().stream()
                .map(preschool -> EntityModel.of(preschool,
                        linkTo(methodOn(PreschoolController.class).addPreschool(preschool.getId())).withSelfRel(),
                        linkTo(methodOn(PreschoolController.class).listOfPreschools()).withRel("preschools")))
                .collect(Collectors.toList());

        return CollectionModel.of(preschools, linkTo(methodOn(PreschoolController.class).listOfPreschools()).withSelfRel());
    }

    @PostMapping("/preschools")
    Preschool newPreschool(@RequestBody Preschool newPreschool)
    {
        return preschoolRepository.save(newPreschool);
    }

    @GetMapping("/preschools/{id}")
    EntityModel<Preschool> addPreschool(@PathVariable Integer id)
    {
        Preschool preschool = preschoolRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));

        return EntityModel.of(preschool,
                linkTo(methodOn(PreschoolController.class).addPreschool(id)).withSelfRel(),
                linkTo(methodOn(PreschoolController.class).listOfPreschools()).withRel("preschools"));
    }

    @PutMapping("/preschools/{id}")
    Preschool replacePreschool(@RequestBody Preschool newPreschool, @PathVariable Integer id)
    {
        return preschoolRepository.findById(id)
                .map(preschool -> {
                    preschool.setPreschoolName(newPreschool.getPreschoolName());
                    preschool.setPrice(newPreschool.getPrice());
                    preschool.setEndOfEarlyRegistrationDate(newPreschool.getEndOfEarlyRegistrationDate());
                    return preschoolRepository.save(preschool);
                })
                .orElseGet(() -> {
                    newPreschool.setId(id);
                    return preschoolRepository.save(newPreschool);
                });
    }

    @DeleteMapping("/preschools/{id}")
    void deletePreschool(@PathVariable Integer id) {
        preschoolRepository.deleteById(id);
    }

}