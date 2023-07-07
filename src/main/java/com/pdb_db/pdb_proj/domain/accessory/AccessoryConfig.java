package com.pdb_db.pdb_proj.domain.accessory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.List;

@Configuration
public class AccessoryConfig {

    @Bean
    CommandLineRunner commandLineRunnerAccessory(AccessoryRepository repository)
    {
        return args -> {
            Accessory palica =  new Accessory(
                    1,
                    "Palica",
                    "doplnok",
                    "drevo",
                    "muzske",
                    new java.util.Date(121, Calendar.AUGUST,6)
            );

            Accessory kridla = new Accessory(
                    2,
                    "Krídla",
                    "doplnok",
                    "paperie",
                    "zenske",
                    new java.util.Date(119, Calendar.JANUARY,6));

            repository.saveAll(
                    List.of(palica,kridla)
            );
        };
    }
}
