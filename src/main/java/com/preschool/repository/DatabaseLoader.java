package com.preschool.repository;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;
import com.preschool.model.Discount;
import com.preschool.model.DiscountValues;
import com.preschool.model.Preschool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 */
@Transactional
@Component
public class DatabaseLoader implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    private final DiscountRepository discountRepository;

    private final PreschoolRepository preschoolRepository;

    private final DiscountValuesRepository discountValuesRepository;

    public DatabaseLoader(DiscountRepository discountRepository, PreschoolRepository preschoolRepository, DiscountValuesRepository discountValuesRepository) {
        this.discountRepository = discountRepository;
        this.preschoolRepository = preschoolRepository;
        this.discountValuesRepository = discountValuesRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Preschool preschoolM = new Preschool("M Lalebahçesi",
                "10/01/2020", 1000);

        Preschool preschoolY = new Preschool("Y Lalebahçesi",
                "11/01/2020", 1500);
        log.info("Preloading Preschool "+ preschoolRepository.save(preschoolM));
        log.info("Preloading Preschool "+ preschoolRepository.save(preschoolY));

        DiscountValues discountValuesM = new DiscountValues(preschoolM,20L);
        DiscountValues discountValuesY = new DiscountValues(preschoolY,15L);


        Discount discount =  new Discount("Erken Kayıt Indirimi", "PERCENTAGE",
                new ArrayList<UserType>(Arrays.asList(UserType.IHVAN, UserType.PERSONEL,
                        UserType.STANDART)),
                "NONE" );

        discount.setDiscountValues(new ArrayList<>() {{
            add(discountValuesM);
            add(discountValuesY);
        }});
        log.info("Preloading Discount" + discountRepository.save(discount));
        log.info("Preloading Discount Values" + discountValuesRepository.save(discountValuesM));
        log.info("Preloading Discount Values" + discountValuesRepository.save(discountValuesY));

        Discount discountIhvan =  new Discount("Ihvan Indirimi","PERCENTAGE",
                new ArrayList<UserType>(List.of(UserType.IHVAN)),
                "NONE" );

        DiscountValues discountsIhvanOfPreschoolM = new DiscountValues(preschoolM,5L);
        DiscountValues discountsIhvanOfPreschoolY = new DiscountValues(preschoolY,10L);

        discountIhvan.setDiscountValues(new ArrayList<>() {{
            add(discountsIhvanOfPreschoolM);
            add(discountsIhvanOfPreschoolY);
        }});

        log.info("Preloading Discount" + discountRepository.save(discountIhvan));
        log.info("Preloading Discount Values" + discountValuesRepository.save(discountsIhvanOfPreschoolM));
        log.info("Preloading Discount Values" + discountValuesRepository.save(discountsIhvanOfPreschoolY));


    }
}
