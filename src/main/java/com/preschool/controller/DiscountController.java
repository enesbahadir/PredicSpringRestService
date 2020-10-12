package com.preschool.controller;

import com.preschool.model.Discount;
import com.preschool.repository.DiscountRepository;
import com.preschool.exeption.PreschoolNotFoundExection;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DiscountController {

    private final DiscountRepository discountRepository;

    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping("/discounts")
    CollectionModel<EntityModel<Discount>> all()
    {
        List<EntityModel<Discount>> discounts = discountRepository.findAll().stream()
                .map(discount -> EntityModel.of(discount,
                        linkTo(methodOn(DiscountController.class).one(discount.getId())).withSelfRel(),
                        linkTo(methodOn(DiscountController.class).all()).withRel("discounts")))
                .collect(Collectors.toList());

        return CollectionModel.of(discounts, linkTo(methodOn(DiscountController.class).all()).withSelfRel());
    }

    @GetMapping("/discounts/{id}")
    EntityModel<Discount> one(@PathVariable Integer id)
    {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));

        return EntityModel.of(discount,
                linkTo(methodOn(DiscountController.class).one(id)).withSelfRel(),
                linkTo(methodOn(DiscountController.class).all()).withRel("discounts"));
    }

    @PostMapping("/discounts")
    Discount newDiscount(@RequestBody Discount newDiscount)
    {
        return discountRepository.save(newDiscount);
    }

    @DeleteMapping("/discounts/{id}")
    void deleteDiscount(@PathVariable Integer id) {
        discountRepository.deleteById(id);
    }

    @PutMapping("/discounts/{id}")
    Discount replaceDiscount(@RequestBody Discount newDiscount, @PathVariable Integer id)
    {
        return discountRepository.findById(id)
                .map(discount -> {
                    discount.setDiscountName(newDiscount.getDiscountName());
                    discount.setDiscountType(newDiscount.getDiscountType());
                    discount.setOrganizationName(newDiscount.getOrganizationName());
                    //discount.setPreschoolNamesAndTheirDiscounts(newDiscount.getPreschoolNamesAndTheirDiscounts());
                    discount.setTypeOfUser(newDiscount.getTypeOfUser());
                    return discountRepository.save(discount);
                })
                .orElseGet(() -> {
                    newDiscount.setId(id);
                    return discountRepository.save(newDiscount);
                });
    }

}
