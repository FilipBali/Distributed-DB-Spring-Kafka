package com.pdb_db.pdb_proj.tests.recenzia;

import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReview;
import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReviewRepository;
import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReviewService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccessoryRecenziaTest
{
    @Autowired
    private AccessoryReviewRepository repository;

    @Autowired
    private AccessoryReviewService service;


    @Test
    @Order(1)
    void create_review()
    {
        AccessoryReview r = new AccessoryReview("Strasne super","Na jednotku",0,0,1,1);
        service.addNewAccessoryReview(r);

        boolean exists = false;
        if(repository.findById(r.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void add_like_to_review ()
    {
        Integer id =3;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        AccessoryReview r = repository.findById(id).get();
        service.updateAccessoryReview(id,null,null,1,null,null,null);

        r = repository.findById(id).get();
        assertThat(r.getLike_reaction().equals(1)).isTrue();
        assertThat(r.getDislike_reaction().equals(0)).isTrue();
    }

    @Test
    @Order(3)
    void clear_like_to_review()
    {
        Integer id =3;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        AccessoryReview r = repository.findById(id).get();
        service.updateAccessoryReview(id,null,null,0,null,null,null);

        r = repository.findById(id).get();
        assertThat(r.getLike_reaction().equals(0)).isTrue();
        assertThat(r.getDislike_reaction().equals(0)).isTrue();
    }

    @Test
    @Order(4)
    void update_review()
    {
        Integer id =3;
        String popis = "novy popis";

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        AccessoryReview r = repository.findById(id).get();
        service.updateAccessoryReview(id,null,popis,null,3,null,null);

        r = repository.findById(id).get();
        assertThat(r.getDescription().equals(popis)).isTrue();
        assertThat(r.getDescription().equals("Na jednotku")).isFalse();
    }

    @Test
    @Order(5)
    void delete_review()
    {
        Integer id =3;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        service.deleteAccessoryReview(id);

        exists = false;
        if(repository.findById(id).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }

}
