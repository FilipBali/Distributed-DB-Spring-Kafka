package com.pdb_db.pdb_proj.tests.recenzia;

import com.pdb_db.pdb_proj.domain.costume_review.CostumeReview;
import com.pdb_db.pdb_proj.domain.costume_review.CostumeReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class CostumeRecenziaRepositoryTest
{

    @Autowired
    private CostumeReviewRepository repository;

    Integer uzivID = 1;
    Integer uzivID2 = 15;
    Integer kostymID = 2;
    Integer kostymID2 = 5;

    @Test
    void create_kostym_recenzia()
    {

        CostumeReview r = new CostumeReview("Strasne super","Na jednotku",0,0,1,1);
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
        if(repository.findCostumeById(kostymID).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findCostumeById(kostymID2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

}
