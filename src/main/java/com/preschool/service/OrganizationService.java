package com.preschool.service;

import com.preschool.controller.OrganizationController;
import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.Organization;
import com.preschool.model.Preschool;
import com.preschool.repository.OrganizationRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    /**
     * Anaokulu oluşturma metodu, ilgili anaokulunu repository'e ekler
     * @param model Oluşturulacak olan anaokulu
     */
    public Organization createOrganization (Organization model)
    {
        Organization newOrganization = new Organization(model.getName());
        organizationRepository.save(newOrganization);
        return newOrganization;
    }

    /**
     * Anaokulu listesi oluşturma metodu, ilgili listeyi repository'den oluşturur
     * @return Anaokulu listesini HATEOAS uygun olarak döner
     */
    public List<EntityModel<Organization>> getOrganizations ()
    {
        return organizationRepository.findAll().stream()
                .map(organization -> EntityModel.of(organization,
                        linkTo(methodOn(OrganizationController.class)
                                .getOrganizationById(organization.getId())).withSelfRel(),
                        linkTo(methodOn(OrganizationController.class)
                                .getOrganizations()).withRel("organizations"))).collect(Collectors.toList());
    }

    /**
     * İstenen anaokulunu verilen id'ye göre getiren metod
     * @param id ilgili anaokulunun id'si
     * @return ilgili anaokulu nesnesi
     */
    public Organization getOrganizationById(int id)
    {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }

    /**
     *
     * @param newOrganization
     * @param id
     * @return
     */
    @Transactional
    public Organization updateOrganization( Organization newOrganization, int id)
    {
        return organizationRepository.findById(id)
                .map(organization -> {
                    organization.setName(newOrganization.getName());
                    return organizationRepository.save(organization);
                })
                .orElseGet(() -> {
                    newOrganization.setId(id);
                    return organizationRepository.save(newOrganization);
                });
    }

    /**
     * Silinmek istenen anaokulunu verilen id'ye göre silen metod
     * @param id ilgili anaokulunun id'si
     */
    public void deleteOrganization(int id) {
        organizationRepository.deleteById(id);
    }
}
