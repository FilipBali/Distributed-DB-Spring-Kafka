package com.pdb_db.pdb_proj.tests.recenzia;

import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReview;
import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class AccessoryRecenziaRepositoryTest
{

    @Autowired
    private AccessoryReviewRepository repository;

    Integer uzivID = 1;
    Integer uzivID2 = 15;
    Integer doplnokID = 2;
    Integer doplnokID2 = 5;

    @Test
    void create_doplnok_recenzia()
    {

        AccessoryReview r = new AccessoryReview("Strasne super","Na jednotku",0,0,uzivID,doplnokID);
        repository.save(r);

        boolean exists = false;
        if(repository.findById(r.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void check_uzivId()
    {
        boolean exists = false;
        if(repository.findCustomerById(uzivID).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findCustomerById(uzivID2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
   }

    @Test
    void check_doplnokId()
    {
        boolean exists = false;
        if(repository.findAccessoryById(doplnokID).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findAccessoryById(doplnokID2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

}
