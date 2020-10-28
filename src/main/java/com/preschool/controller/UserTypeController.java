package com.preschool.controller;

import com.preschool.model.Preschool;
import com.preschool.model.UserType;
import com.preschool.service.UserTypeService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserTypeController {

    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    /**
     * İstenen anaokulunu id üzerinden arayarak ilgili anaokulunun dönen metod
     * @param id ilgili anaokulununu id'si
     * @return HATEOAS uygun olarak ilgili anaokulunu döner
     */
    @GetMapping("/usertypes/{id}")
    public EntityModel<UserType> getUserTypeById (@PathVariable Integer id)
    {
        UserType userType = userTypeService.getUserTypeById(id);

        return EntityModel.of(userType,
                linkTo(methodOn(UserTypeController.class).getUserTypeById(id)).withSelfRel(),
                linkTo(methodOn(UserTypeController.class).getUserTypes()).withRel("usertypes"));
    }

    /**
     * Anaokulu listesine erişmek için çağrılan GET metodu
     * @return HATEOAS uygun olarak anaokulu listesini döner
     */
    @GetMapping("/usertypes")
    public CollectionModel<EntityModel<UserType>> getUserTypes()
    {
        List<EntityModel<UserType>> userTypes = userTypeService.getUserTypes();
        return CollectionModel.of(userTypes, linkTo(methodOn(UserTypeController.class)
                .getUserTypes()).withSelfRel());
    }

    /**
     *
     * @param newUserType
     * @return
     */
    @PostMapping("/usertypes")
    public EntityModel<UserType> createUserType (@RequestBody UserType newUserType)
    {
        newUserType = userTypeService.createUserType(newUserType);
        return EntityModel.of(newUserType,
                linkTo(methodOn(UserTypeController.class).getUserTypeById(newUserType.getId())).withSelfRel(),
                linkTo(methodOn(UserTypeController.class).getUserTypes()).withRel("usertypes"));
    }

    /**
     *
     * @param newUserType
     * @param id
     * @return
     */
    @PutMapping("/usertypes/{id}")
    public EntityModel<UserType> updateUserType (@RequestBody UserType newUserType, @PathVariable Integer id)
    {
        UserType userType = userTypeService.updateUserType(newUserType, id);
        return EntityModel.of(userType,
                linkTo(methodOn(UserTypeController.class).getUserTypeById(id)).withSelfRel(),
                linkTo(methodOn(UserTypeController.class).getUserTypes()).withRel("usertypes"));
    }

    /**
     * Anaokulu silmek için çağırılan DELETE metodu
     * @param id silinecek olan anaokulun id'si
     */
    @DeleteMapping("/usertypes/{id}")
    void deletePreschool(@PathVariable Integer id) {
        userTypeService.deletePreschool(id);
    }

}
