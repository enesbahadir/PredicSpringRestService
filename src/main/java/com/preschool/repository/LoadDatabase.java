package com.preschool.repository;

import com.preschool.enums.DiscountType;
import com.preschool.enums.OrganizationNames;
import com.preschool.enums.UserType;
import com.preschool.model.Discount;
import com.preschool.model.Preschool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     *
     * @param preschoolRepository
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(IPreschoolRepository preschoolRepository)
    {
        return args -> {
            log.info("Preloading "+ preschoolRepository.save(
                    new Preschool("M Lalebahçesi","01/10/2020", 1000,1)));
            log.info("Preloading "+ preschoolRepository.save(
                    new Preschool("Y Lalebahçesi","01/11/2020", 1500,2)));

        };
    }

    @Bean
    CommandLineRunner initDatabase(IDiscountRepository discountRepository)
    {
        return args -> {
            log.info("Preloading "+ discountRepository.save(
                    new Discount("Erken Kayıt İndirimi", DiscountType.PERCENTAGE,
                            new ArrayList<UserType>(Arrays.asList(UserType.IHVAN, UserType.PERSONEL, UserType.STANDART)),
                            OrganizationNames.NONE, new HashMap<>())));

        };
    }
}
