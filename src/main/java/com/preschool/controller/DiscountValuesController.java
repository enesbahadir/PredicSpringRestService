package com.preschool.controller;

import com.preschool.model.Discount;
import com.preschool.model.DiscountValues;
import com.preschool.service.DiscountValuesService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DiscountValuesController {

    private final DiscountValuesService discountValuesService;

    public DiscountValuesController(DiscountValuesService discountValuesService) {
        this.discountValuesService = discountValuesService;
    }

    @PostMapping("/values")
    public EntityModel<DiscountValues> createDiscountValue(@RequestBody DiscountValues discountValues)
    {
        DiscountValues newDiscountValue = discountValuesService.createDiscountValue(discountValues);
        return EntityModel.of(newDiscountValue,
                linkTo(methodOn(DiscountValuesController.class).getDiscountValueById(newDiscountValue.getId())).withSelfRel(),
                linkTo(methodOn(DiscountValuesController.class).getDiscountValues()).withRel("values"));
    }


    @GetMapping("/values")
    public CollectionModel<EntityModel<DiscountValues>> getDiscountValues()
    {
        List<EntityModel<DiscountValues>> discountValues = discountValuesService.getDiscountValues();
        return CollectionModel.of(discountValues, linkTo(methodOn(DiscountValuesController.class)
                .getDiscountValues()).withSelfRel());
    }


    @GetMapping("/values/{id}")
    public EntityModel<DiscountValues> getDiscountValueById(@PathVariable Integer id)
    {
        DiscountValues discountValue = discountValuesService.getDiscountById(id);

        return EntityModel.of(discountValue,
                linkTo(methodOn(DiscountValuesController.class).getDiscountValueById(id)).withSelfRel(),
                linkTo(methodOn(DiscountValuesController.class).getDiscountValues()).withRel("values"));
    }

}
