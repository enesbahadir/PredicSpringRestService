package com.preschool.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import com.preschool.model.Preschool;
import com.preschool.service.PreschoolService;
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

    private final PreschoolService preschoolService;

    public PreschoolController(PreschoolService preschoolService) {
        this.preschoolService = preschoolService;
    }

    /**
     * Anaokulu listesine erişmek için çağrılan GET metodu
     * @return HATEOAS uygun olarak anaokulu listesini döner
     */
    @GetMapping("/preschools")
    public CollectionModel<EntityModel<Preschool>> getPreschools()
    {
        List<EntityModel<Preschool>> preschools = preschoolService.getPreschools();
        return CollectionModel.of(preschools, linkTo(methodOn(PreschoolController.class)
                .getPreschools()).withSelfRel());
    }

    /**
     * Anaokulu oluşturmak için çağrılan POST metodu
     * @param newPreschool yeni oluşturulan olan anaokulu nesnesi
     * @return HATEOAS uygun olarak eklenen anaokulunu döne r
     */
    @PostMapping("/preschools")
    public EntityModel<Preschool> createPreschool (@RequestBody Preschool newPreschool)
    {
        newPreschool = preschoolService.createPreschool(newPreschool);
        return EntityModel.of(newPreschool,
                linkTo(methodOn(PreschoolController.class).getPreschoolById(newPreschool.getId())).withSelfRel(),
                linkTo(methodOn(PreschoolController.class).getPreschools()).withRel("preschools"));
    }

    /**
     * İstenen anaokulunu id üzerinden arayarak ilgili anaokulunun dönen metod
     * @param id ilgili anaokulununu id'si
     * @return HATEOAS uygun olarak ilgili anaokulunu döner
     */
    @GetMapping("/preschools/{id}")
    public EntityModel<Preschool> getPreschoolById (@PathVariable Integer id)
    {
        Preschool preschool = preschoolService.getPreschoolById(id);

        return EntityModel.of(preschool,
                linkTo(methodOn(PreschoolController.class).getPreschoolById(id)).withSelfRel(),
                linkTo(methodOn(PreschoolController.class).getPreschools()).withRel("preschools"));
    }

    /**
     * Anaokulu düzenlemek için çağırılan PUT metodu
     * @param newPreschool değişiklik yapılmış olan anaokulu nesnesi
     * @param id değiştirilecek olan anaokulunun id'si
     * @return HATEOAS uygun olarak düzenlenen yeni anaokulunu döner
     */
    @PutMapping("/preschools/{id}")
    public EntityModel<Preschool> updatePreschool (@RequestBody Preschool newPreschool, @PathVariable Integer id)
    {
        Preschool preschool = preschoolService.updatePreschool(newPreschool, id);
        return EntityModel.of(preschool,
                linkTo(methodOn(PreschoolController.class).getPreschoolById(id)).withSelfRel(),
                linkTo(methodOn(PreschoolController.class).getPreschools()).withRel("preschools"));
    }

    /**
     * Anaokulu silmek için çağırılan DELETE metodu
     * @param id silinecek olan anaokulun id'si
     */
    @DeleteMapping("/preschools/{id}")
    void deletePreschool(@PathVariable Integer id) {
        preschoolService.deletePreschool(id);
    }

}