package com.pdb_db.pdb_proj.domainMongo.accessory_reservation_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class AccessoryReservationConfigM {
    @Bean
    CommandLineRunner commandLineRunnerAccessoryReservationM(AccessoryReservationRepositoryM repository)
    {
        return args -> {
            AccessoryReservationM jedna = new AccessoryReservationM(
                    1,
                    1,
                    1,
                    new java.util.Date(120, Calendar.APRIL,6),
                    new java.util.Date(120, Calendar.JULY,15),
                    1);
            AccessoryReservationM dva = new AccessoryReservationM(
                    2,
                    2,
                    2,
                    new java.util.Date(120,Calendar.OCTOBER,7),
                    new java.util.Date(120,Calendar.NOVEMBER,20),
                    1);
            repository.save(jedna);
            repository.save(dva);
        };
    }
}
