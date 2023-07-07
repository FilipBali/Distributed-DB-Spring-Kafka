package com.pdb_db.pdb_proj.domainMongo.accessory_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class AccessoryConfigM
{
    @Bean
    CommandLineRunner commandLineRunnerAccessoryM(AccessoryRepositoryM repository)
    {
        return args -> {
            AccessoryM palica =  new AccessoryM(
                    1,
                    "Palica",
                    "doplnok",
                    "drevo",
                    "muzske",
                    new java.util.Date(121, Calendar.AUGUST,6)
            );

            AccessoryM kridla = new AccessoryM(
                    2,
                    "Krídla",
                    "doplnok",
                    "paperie",
                    "zenske",
                    new java.util.Date(119, Calendar.JANUARY,6));

            repository.save(palica);
            repository.save(kridla);
        };
    }
}
