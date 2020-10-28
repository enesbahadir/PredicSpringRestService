package com.preschool.controller;

import com.preschool.model.Organization;
import com.preschool.service.OrganizationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * İstenen anaokulunu id üzerinden arayarak ilgili anaokulunun dönen metod
     * @param id ilgili anaokulununu id'si
     * @return HATEOAS uygun olarak ilgili anaokulunu döner
     */
    @GetMapping("/organizations/{id}")
    public EntityModel<Organization> getOrganizationById (@PathVariable Integer id)
    {
        Organization organization = organizationService.getOrganizationById(id);

        return EntityModel.of(organization,
                linkTo(methodOn(OrganizationController.class).getOrganizationById(id)).withSelfRel(),
                linkTo(methodOn(OrganizationController.class).getOrganizations()).withRel("organizations"));
    }

    /**
     * Anaokulu listesine erişmek için çağrılan GET metodu
     * @return HATEOAS uygun olarak anaokulu listesini döner
     */
    @GetMapping("/organizations")
    public CollectionModel<EntityModel<Organization>> getOrganizations()
    {
        List<EntityModel<Organization>> organization = organizationService.getOrganizations();
        return CollectionModel.of(organization, linkTo(methodOn(OrganizationController.class)
                .getOrganizations()).withSelfRel());
    }

    /**
     *
     * @param newOrganization
     * @return
     */
    @PostMapping("/organizations")
    public EntityModel<Organization> createOrganization (@RequestBody Organization newOrganization)
    {
        newOrganization = organizationService.createOrganization(newOrganization);
        return EntityModel.of(newOrganization,
                linkTo(methodOn(OrganizationController.class)
                        .getOrganizationById(newOrganization.getId())).withSelfRel(),
                linkTo(methodOn(OrganizationController.class).getOrganizations()).withRel("usertypes"));
    }

    /**
     *
     * @param newOrganization
     * @param id
     * @return
     */
    @PutMapping("/organizations/{id}")
    public EntityModel<Organization> updateOrganization
    (@RequestBody Organization newOrganization, @PathVariable Integer id)
    {
        Organization organization = organizationService.updateOrganization(newOrganization, id);
        return EntityModel.of(organization,
                linkTo(methodOn(OrganizationController.class).getOrganizationById(id)).withSelfRel(),
                linkTo(methodOn(OrganizationController.class).getOrganizations()).withRel("organizations"));
    }

    /**
     * Anaokulu silmek için çağırılan DELETE metodu
     * @param id silinecek olan anaokulun id'si
     */
    @DeleteMapping("/organizations/{id}")
    void deleteOrganization(@PathVariable Integer id) {
        organizationService.deleteOrganization(id);
    }

}
