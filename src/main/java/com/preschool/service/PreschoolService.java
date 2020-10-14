package com.preschool.service;

import com.preschool.controller.PreschoolController;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.Preschool;
import com.preschool.repository.PreschoolRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PreschoolService {

    private final PreschoolRepository preschoolRepository;

    public PreschoolService(PreschoolRepository preschoolRepository) {
        this.preschoolRepository = preschoolRepository;
    }

    /**
     * Anaokulu oluşturma metodu, ilgili anaokulunu repository'e ekler
     * @param model Oluşturulacak olan anaokulu
     */
    public void createPreschool (Preschool model)
    {
        preschoolRepository.save(model);
    }

    /**
     * Anaokulu listesi oluşturma metodu, ilgili listeyi repository'den oluşturur
     * @return Anaokulu listesini HATEOAS uygun olarak döner
     */
    public List<EntityModel<Preschool>> getPreschools ()
    {
        return preschoolRepository.findAll().stream()
                .map(preschool -> EntityModel.of(preschool,
                        linkTo(methodOn(PreschoolController.class).getPreschoolById(preschool.getId())).withSelfRel(),
                        linkTo(methodOn(PreschoolController.class).getPreschools()).withRel("preschools")))
                .collect(Collectors.toList());
    }

    /**
     * İstenen anaokulunu verilen id'ye göre getiren metod
     * @param id ilgili anaokulunun id'si
     * @return ilgili anaokulu nesnesi
     */
    public Preschool getPreschoolById(int id)
    {
        return preschoolRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }

    /**
     * Düzenlenmek istenen anaokulu, verilen id ve yeni anaokulu nesnesine göre düzenleyen metod
     * @param newPreschool değişiklik yapılmış anaokulu nesnesi
     * @param id değişiklik yapılmış olan anaokulunun id'si
     * @return değişiklik yapılmış olan anaokulunu nesnesini döner
     */
    @Transactional
    public Preschool updatePreschool( Preschool newPreschool, int id)
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

    /**
     * Silinmek istenen anaokulunu verilen id'ye göre silen metod
     * @param id ilgili anaokulunun id'si
     */
    public void deletePreschool(int id) {
        preschoolRepository.deleteById(id);
    }


}
