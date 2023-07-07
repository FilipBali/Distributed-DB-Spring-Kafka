package com.pdb_db.pdb_proj.domain.costume_reservation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Calendar;
import java.util.List;

@Configuration
public class CostumeReservationConfig {

    @Bean
    CommandLineRunner commandLineRunnerCostumeReservation(CostumeReservationRepository repository)
    {
        return args -> {
            CostumeReservation firstRes =  new CostumeReservation(1,
                    1,
                    1,
                    new java.util.Date(118, Calendar.JUNE,1),
                    new java.util.Date(118,Calendar.AUGUST,8),
                    1);
            CostumeReservation secondRes =  new CostumeReservation(2,
                    2,
                    2,
                    new java.util.Date(119, Calendar.JULY,5),
                    new java.util.Date(119,Calendar.SEPTEMBER,15),
                    1
            );
            repository.saveAll(
                    List.of(firstRes,secondRes)
            );
        };
    }

}
