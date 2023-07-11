package com.pdb_db.pdb_proj.tests.review;

import com.pdb_db.pdb_proj.domain.costume_review.CostumeReview;
import com.pdb_db.pdb_proj.domain.costume_review.CostumeReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class CostumeReviewRepositoryTest
{

    @Autowired
    private CostumeReviewRepository repository;

    Integer customerId1 = 1;
    Integer customerId2 = 15;
    Integer costumeId1 = 2;
    Integer costumeId2 = 5;

    @Test
    void createCostumeReview()
    {

        CostumeReview r = new CostumeReview(
                "Strasne super",
                "Na jednotku",
                0,
                0,
                1,
                1);
        repository.save(r);

        boolean exists = false;
        if(repository.findById(r.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void checkCustomer()
    {
        boolean exists = false;
        if(repository.findCustomerById(customerId1).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findCustomerById(customerId2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

    @Test
    void checkCostume()
    {
        boolean exists = false;
        if(repository.findCostumeById(costumeId1).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findCostumeById(costumeId2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

}
