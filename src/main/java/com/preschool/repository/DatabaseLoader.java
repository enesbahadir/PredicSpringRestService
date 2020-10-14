package com.preschool.repository;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;
import com.preschool.model.Discount;
import com.preschool.model.DiscountsOfPreschool;
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

    public DatabaseLoader(DiscountRepository discountRepository, PreschoolRepository preschoolRepository) {
        this.discountRepository = discountRepository;
        this.preschoolRepository = preschoolRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Preschool preschoolM = new Preschool("M Lalebahçesi",
                "01/10/2020", 1000);

        Preschool preschoolY = new Preschool("Y Lalebahçesi",
                "01/11/2020", 1500);
        log.info("Preloading Preschool "+ preschoolRepository.save(preschoolM));
        log.info("Preloading Preschool "+ preschoolRepository.save(preschoolY));

        DiscountsOfPreschool discountsOfPreschoolM = new DiscountsOfPreschool(preschoolM,20L);
        DiscountsOfPreschool discountsOfPreschoolY = new DiscountsOfPreschool(preschoolY,15L);

        Discount discount =  new Discount("Erken Kayıt Indirimi", DiscountType.PERCENTAGE,
                new ArrayList<>(Arrays.asList(UserType.IHVAN, UserType.PERSONEL, UserType.STANDART)),
                OrganizationNames.NONE );

        discount.setDiscountsOfPreschool(new ArrayList<>() {{
            add(discountsOfPreschoolM);
            add(discountsOfPreschoolY);
        }});
        log.info("Preloading Discount" + discountRepository.save(discount));

        Discount discountIhvan =  new Discount("Ihvan Indirimi", DiscountType.PERCENTAGE,
                new ArrayList<>(Arrays.asList(UserType.IHVAN)),
                OrganizationNames.NONE );

        DiscountsOfPreschool discountsIhvanOfPreschoolM = new DiscountsOfPreschool(preschoolM,5L);
        DiscountsOfPreschool discountsIhvanOfPreschoolY = new DiscountsOfPreschool(preschoolY,10L);

        discountIhvan.setDiscountsOfPreschool(new ArrayList<>() {{
            add(discountsIhvanOfPreschoolM);
            add(discountsIhvanOfPreschoolY);
        }});

        log.info("Preloading Discount" + discountRepository.save(discountIhvan));
    }
}
