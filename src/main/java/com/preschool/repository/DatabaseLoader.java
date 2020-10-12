package com.preschool.repository;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;
import com.preschool.model.Discount;
import com.preschool.model.Preschool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Component
//@Service
//@Transactional
//@Repository
//@RestController
public class DatabaseLoader implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    private final DiscountRepository discountRepository;

    private final PreschoolRepository preschoolRepository;

    public DatabaseLoader(DiscountRepository discountRepository, PreschoolRepository preschoolRepository) {
        this.discountRepository = discountRepository;
        this.preschoolRepository = preschoolRepository;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Preloading "+ preschoolRepository.save(
                new Preschool("M Lalebahçesi","01/10/2020", 1000,1)));
        log.info("Preloading "+ preschoolRepository.save(
                new Preschool("Y Lalebahçesi","01/11/2020", 1500,2)));
        /*
        log.info("Preloading "+ discountRepository.save(
                new Discount("Erken Kayıt İndirimi", DiscountType.PERCENTAGE,
                        new ArrayList<UserType>(Arrays.asList(UserType.IHVAN, UserType.PERSONEL, UserType.STANDART)),
                        OrganizationNames.NONE, new HashMap<>())));
        */
    }
}
