package com.pdb_db.pdb_proj.tests.reservation;

import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservation;
import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CostumeReservationRepositoryTest
{

    @Autowired
    private CostumeReservationRepository repository;

    @Test
    void createReservationNoUser()
    {

        CostumeReservation r = new CostumeReservation(
                1,
                2,
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
      List<CostumeReservation> list = repository.findAll();

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
        CostumeReservation r = repository.findById(1).get();

        boolean exists = repository.findCustomerById(r.getCustumerId()).isPresent();
        assertThat(exists).isTrue();

    }
}
