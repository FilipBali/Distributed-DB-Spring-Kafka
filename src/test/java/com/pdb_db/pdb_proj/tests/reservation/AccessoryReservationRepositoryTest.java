package com.pdb_db.pdb_proj.tests.reservation;

import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservation;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccessoryReservationRepositoryTest
{
    @Autowired
    AccessoryReservationRepository repository;

    @Test
    void createReservationNoCustomer()
    {

        AccessoryReservation r = new AccessoryReservation(2,
                1,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()),
                0);

        repository.save(r);

        boolean exists = false;
        if(repository.findById(r.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void getAllReservations()
    {
        List<AccessoryReservation> list = repository.findAll();

        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach(
                l ->
                { if(repository.findById(l.getId()).isPresent())
                {
                    exists.set(true);}
                    assertThat(exists.get()).isTrue();
                    exists.set(false);
                }
        );
    }

    @Test
    void checkCustomer()
    {
        AccessoryReservation r = repository.findById(1).get();

        boolean exists = repository.findCustomerById(r.getCustomerId()).isPresent();
        assertThat(exists).isTrue();

        exists = false;

    }
}
