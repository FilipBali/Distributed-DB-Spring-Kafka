package com.pdb_db.pdb_proj.tests.reservation;

import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservation;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservationRepository;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccessoryReservationTest {
    @Autowired
    AccessoryReservationRepository repository;

    @Autowired
    AccessoryReservationService service;


    @Test
    @Order(1)
    void createReservation()
    {
        AccessoryReservation r = new AccessoryReservation(
                2,
                1,
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()),
               1);

        repository.save(r);

        boolean exists = false;
        if(repository.findById(r.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void showOngoingReservations()
    {
        Integer id = 3;
        List<AccessoryReservation> list = service.getAllOngoing();

        list.forEach( l->
                {
                    assertThat(l.getIsReturned().equals(0)).isTrue();
                }
        );

        assertThat(list.contains(repository.findById(id).get()));
    }

    @Test
    @Order(3)
    void endReservation()
    {
        Integer id = 3;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        service.updateAccessoryReservation(
                id,
                null,
                null,
                null,
                null,
                1);

        assertThat( repository.findById(id).get().getIsReturned().equals(1)).isTrue();
    }

    @Test
    @Order(4)
    void showFinishedReservation()
    {
        Integer id = 3;
        List<AccessoryReservation> list = service.getAllOngoing();

        list.forEach( l->
                {
                    assertThat(l.getIsReturned().equals(1)).isTrue();
                }
        );
        assertThat(list.contains(repository.findById(id).get()));
    }


    @Test
    @Order(5)
    void deleteReservation()
    {
        Integer id = 3;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        service.deleteAccessoryReservation(id);

        exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isFalse();
    }
}
