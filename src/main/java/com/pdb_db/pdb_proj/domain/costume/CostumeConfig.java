package com.pdb_db.pdb_proj.domain.costume;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Calendar;
import java.util.List;

@Configuration
public class CostumeConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCostume(CostumeRepository repository)
    {
        return args -> {
            Costume pastier =  new Costume(
                    1,
                    "Pastier",
                    "kostym",
                    "bavlna",
                    "muzske",
                    40,
                    new java.util.Date(122, Calendar.MARCH,1));

            Costume anjel =  new Costume(
                    2,
                    "Anjel",
                    "kostym",
                    "saten",
                    "zenske",
                    36 ,
                    new java.util.Date(121, Calendar.JANUARY,1));

            repository.saveAll(
                    List.of(pastier,anjel)
            );
        };
    }
}



