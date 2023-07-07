package com.pdb_db.pdb_proj.domainMongo.costume_reservation_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class CostumeReservationConfigM {

    @Bean
    CommandLineRunner commandLineRunnerCostumeReservationM(CostumeReservationRepositoryM repository)
    {
        return args -> {
            CostumeReservationM firstRes = new CostumeReservationM(
                    1,
                    1,
                    1,
                    new java.util.Date(118,Calendar.JUNE,1),
                    new java.util.Date(118,Calendar.AUGUST,8),
                    1);
            CostumeReservationM secondRes = new CostumeReservationM(
                    2,
                    2,
                    2,
                    new java.util.Date(119, Calendar.JULY,5),
                    new java.util.Date(119,Calendar.SEPTEMBER,15),
                    1
            );
            repository.save(firstRes);
            repository.save(secondRes);
        };
    }
}
