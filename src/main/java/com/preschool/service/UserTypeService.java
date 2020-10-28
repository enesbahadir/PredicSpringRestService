package com.preschool.service;

import com.preschool.controller.PreschoolController;
import com.preschool.controller.UserTypeController;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.Preschool;
import com.preschool.model.UserType;
import com.preschool.repository.UserTypeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserTypeService {

    private final UserTypeRepository userTypeRepository ;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    /**
     * Anaokulu oluşturma metodu, ilgili anaokulunu repository'e ekler
     * @param model Oluşturulacak olan anaokulu
     */
    public UserType createUserType (UserType model)
    {
        UserType newUserType = new UserType(model.getUserType());
        userTypeRepository.save(newUserType);
        return newUserType;
    }

    /**
     * Anaokulu listesi oluşturma metodu, ilgili listeyi repository'den oluşturur
     * @return Anaokulu listesini HATEOAS uygun olarak döner
     */
    public List<EntityModel<UserType>> getUserTypes()
    {
        return userTypeRepository.findAll().stream()
                .map(userType -> EntityModel.of(userType,
                        linkTo(methodOn(UserTypeController.class)
                                .getUserTypeById(userType.getId())).withSelfRel(),
                        linkTo(methodOn(UserTypeController.class).getUserTypes()).withRel("usertypes")))
                .collect(Collectors.toList());
    }

    /**
     * İstenen anaokulunu verilen id'ye göre getiren metod
     * @param id ilgili anaokulunun id'si
     * @return ilgili anaokulu nesnesi
     */
    public UserType getUserTypeById(int id)
    {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }

    /**
     * Silinmek istenen anaokulunu verilen id'ye göre silen metod
     * @param id ilgili anaokulunun id'si
     */
    public void deletePreschool(int id) {
        userTypeRepository.deleteById(id);
    }

    /**
     *
     * @param newUserType
     * @param id
     * @return
     */
    @Transactional
    public UserType updateUserType( UserType newUserType, int id)
    {
        return userTypeRepository.findById(id)
                .map(userType -> {
                    userType.setUserType(newUserType.getUserType());
                    return userTypeRepository.save(userType);
                })
                .orElseGet(() -> {
                    newUserType.setId(id);
                    return userTypeRepository.save(newUserType);
                });
    }


}
