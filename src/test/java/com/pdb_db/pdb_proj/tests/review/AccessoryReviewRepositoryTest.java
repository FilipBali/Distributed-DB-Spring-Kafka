package com.pdb_db.pdb_proj.tests.review;

import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReview;
import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
public class AccessoryReviewRepositoryTest
{

    @Autowired
    private AccessoryReviewRepository repository;

    Integer customerId1 = 1;
    Integer customerId2 = 15;
    Integer accessoryId1 = 2;
    Integer accessoryId2 = 5;

    @Test
    void createAccessoryReview()
    {

        AccessoryReview r = new AccessoryReview(
                "Strasne super",
                "Na jednotku",
                0,
                0,
                customerId1,
                accessoryId1);
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
    void checkAccessory()
    {
        boolean exists = false;
        if(repository.findAccessoryById(accessoryId1).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        exists = false;
        if(repository.findAccessoryById(accessoryId2).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

}
