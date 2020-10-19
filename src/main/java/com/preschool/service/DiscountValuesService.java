package com.preschool.service;

import com.preschool.controller.DiscountValuesController;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.DiscountValues;
import com.preschool.model.Preschool;
import com.preschool.repository.DiscountValuesRepository;
import com.preschool.repository.PreschoolRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DiscountValuesService {
    private final DiscountValuesRepository discountValuesRepository;
    private final PreschoolRepository preschoolRepository;

    public DiscountValuesService(DiscountValuesRepository discountValuesRepository, PreschoolRepository preschoolRepository) {
        this.discountValuesRepository = discountValuesRepository;
        this.preschoolRepository = preschoolRepository;
    }


    public DiscountValues createDiscountValue(DiscountValues discountValues)
    {
        DiscountValues newDiscountValue = new DiscountValues(discountValues.getPreschool(),
                discountValues.getValue());
        discountValuesRepository.save(newDiscountValue);
        return newDiscountValue;
    }

    /**
     * indirim listesi oluşturma metodu, ilgili listeyi repository'den oluşturur
     * @return HATEOAS uygun olarak indirim listesini döner
     */
    public List<EntityModel<DiscountValues>> getDiscountValues()
    {
        return discountValuesRepository.findAll().stream()
                .map(discountValues -> EntityModel.of(discountValues,
                        linkTo(methodOn(DiscountValuesController.class).getDiscountValueById(discountValues.getId())).withSelfRel(),
                        linkTo(methodOn(DiscountValuesController.class).getDiscountValues()).withRel("values")))
                .collect(Collectors.toList());
    }

    /**
     * İstenen indirimi verilen id'ye göre getiren metod
     * @param id ilgili indirimin id'si
     * @return ilgili indirim nesnesi
     */
    public DiscountValues getDiscountById(int id)
    {
        return discountValuesRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }
}
