package com.pdb_db.pdb_proj.domain.accessory_reservation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.List;

@Configuration
public class AccessoryReservationConfig {

    @Bean
    CommandLineRunner commandLineRunnerAccessoryReservation(AccessoryReservationRepository repository)
    {
        return args -> {
            AccessoryReservation firstRes = new AccessoryReservation(
                    1,
                    1,
                    1,
                    new java.util.Date(120, Calendar.APRIL,6),
                    new java.util.Date(120, Calendar.JULY,15),
                    1);
            AccessoryReservation secondRes = new AccessoryReservation(
                    2,
                    2,
                    2,
                    new java.util.Date(120,Calendar.OCTOBER,7),
                    new java.util.Date(120,Calendar.NOVEMBER,20),
                    1);
            repository.saveAll(
                    List.of(firstRes,secondRes)
            );
        };
    }
}
