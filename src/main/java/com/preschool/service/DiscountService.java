package com.preschool.service;

import com.preschool.controller.DiscountController;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.Discount;
import com.preschool.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DiscountService {
    @Autowired
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * indirim listesi oluşturma metodu, ilgili listeyi repository'den oluşturur
     * @return HATEOAS uygun olarak indirim listesini döner
     */
    public List<EntityModel<Discount>> getDiscounts()
    {
        return discountRepository.findAll().stream()
                .map(discount -> EntityModel.of(discount,
                        linkTo(methodOn(DiscountController.class).getDiscountById(discount.getId())).withSelfRel(),
                        linkTo(methodOn(DiscountController.class).getDiscounts()).withRel("discounts")))
                .collect(Collectors.toList());
    }

    /**
     * İstenen indirimi verilen id'ye göre getiren metod
     * @param id ilgili indirimin id'si
     * @return ilgili indirim nesnesi
     */
    public Discount getDiscountById(int id)
    {
        return discountRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }


    /**
     * İndirim oluşturma metodu, ilgili anaokulunu repository'e ekler
     * @param newDiscount Oluşturulacak olan İndirim
     */
    public void createDiscount(Discount newDiscount)
    {
        discountRepository.save(newDiscount);
    }

    /**
     * Silinmek istenen indirimi verilen id'ye göre silen metod
     * @param id ilgili indirimin id'si
     */
    public void deleteDiscount(int id)
    {
        discountRepository.deleteById(id);
    }

    /**
     * Düzenlenmek istenen indirimi, verilen id ve yeni indirim nesnesine göre düzenleyen metod
     * @param newDiscount değişiklik yapılmış indirim nesnesi
     * @param id değişiklik yapılmış olan indirimin id'si
     * @return değişiklik yapılmış olan indirim nesnesini döner
     */
    public Discount updateDiscount (Discount newDiscount, int id)
    {
        return discountRepository.findById(id)
                .map(discount -> {
                    discount.setDiscountName(newDiscount.getDiscountName());
                    discount.setDiscountType(newDiscount.getDiscountType());
                    discount.setOrganizationName(newDiscount.getOrganizationName());
                    //discount.setPreschoolNamesAndTheirDiscounts(newDiscount.getPreschoolNamesAndTheirDiscounts());
                    discount.setUserType(newDiscount.getUserType());
                    return discountRepository.save(discount);
                })
                .orElseGet(() -> {
                    newDiscount.setId(id);
                    return discountRepository.save(newDiscount);
                });
    }
}
