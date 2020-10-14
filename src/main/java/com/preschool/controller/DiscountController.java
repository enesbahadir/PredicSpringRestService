package com.preschool.controller;

import com.preschool.model.Discount;
import com.preschool.service.DiscountService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     *İndirim listesine erişmek için çağrılan GET metodu
     * @return İndirim listesini HATEOAS uygun olarak döner
     */
    @GetMapping("/discounts")
    public CollectionModel<EntityModel<Discount>> getDiscounts()
    {
        List<EntityModel<Discount>> discounts = discountService.getDiscounts();
        return CollectionModel.of(discounts, linkTo(methodOn(DiscountController.class)
                .getDiscounts()).withSelfRel());
    }

    /**
     *İstenen indirimi id üzerinden arayarak ilgili anaokulunun dönen metod
     * @param id ilgili indirimin id'si
     * @return HATEOAS uygun olarak ilgili indirim nesnesini döner
     */
    @GetMapping("/discounts/{id}")
    public EntityModel<Discount> getDiscountById(@PathVariable Integer id)
    {
        Discount discount = discountService.getDiscountById(id);

        return EntityModel.of(discount,
                linkTo(methodOn(DiscountController.class).getDiscountById(id)).withSelfRel(),
                linkTo(methodOn(DiscountController.class).getDiscounts()).withRel("discounts"));
    }

    /**
     *İndirim oluşturmak için çağrılan POST metodu
     * @param newDiscount yeni oluşturulan olan indirim nesnesi
     * @return HATEOAS uygun olarak ilgili indirim nesnesini döner
     */
    @PostMapping("/discounts")
    public EntityModel<Discount> createDiscount(@RequestBody Discount newDiscount)
    {
        discountService.createDiscount(newDiscount);
        return EntityModel.of(newDiscount,
                linkTo(methodOn(DiscountController.class).getDiscountById(newDiscount.getId())).withSelfRel(),
                linkTo(methodOn(DiscountController.class).getDiscounts()).withRel("discounts"));
    }

    /**
     * İndirim silmek için çağırılan DELETE metodu
     * @param id silinecek olan indirimin id'si
     */
    @DeleteMapping("/discounts/{id}")
    public void deleteDiscount(@PathVariable Integer id) {
        discountService.deleteDiscount(id);
    }

    /**
     * İndirim düzenlemek için çağırılan PUT metodu
     * @param newDiscount değişiklik yapılmış olan indirim nesnesi
     * @param id değiştirilecek olan indirimin id'si
     * @return HATEOAS uygun olarak düzenlenen yeni indirimi döner
     */
    @PutMapping("/discounts/{id}")
    public EntityModel<Discount> updateDiscount(@RequestBody Discount newDiscount, @PathVariable Integer id)
    {
        Discount discount = discountService.updateDiscount(newDiscount, id);
        return EntityModel.of(discount,
                linkTo(methodOn(DiscountController.class).getDiscountById(id)).withSelfRel(),
                linkTo(methodOn(DiscountController.class).getDiscounts()).withRel("discounts"));
    }

}
