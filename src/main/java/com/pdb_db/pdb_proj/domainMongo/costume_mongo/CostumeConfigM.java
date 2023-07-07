package com.pdb_db.pdb_proj.domainMongo.costume_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class CostumeConfigM
{

    @Bean
    CommandLineRunner commandLineRunnerCostumeM(CostumeRepositoryM repository)
    {

        return args -> {
            CostumeM pastier = new CostumeM(
                    1,
                    "Pastier",
                    "kostym",
                    "bavlna",
                    "muzske",
                    40,
                    new java.util.Date(122,Calendar.MARCH,1));

            CostumeM anjel = new CostumeM(
                    2,
                    "Anjel",
                    "kostym",
                    "saten",
                    "zenske",
                    36,
                    new java.util.Date(121, Calendar.JANUARY,1));


            repository.save(pastier);
            repository.save(anjel);

        };
    }
}
