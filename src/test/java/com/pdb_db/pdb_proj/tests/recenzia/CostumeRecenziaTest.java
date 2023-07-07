package com.pdb_db.pdb_proj.tests.recenzia;

import com.pdb_db.pdb_proj.domain.costume_review.CostumeReview;
import com.pdb_db.pdb_proj.domain.costume_review.CostumeReviewRepository;
import com.pdb_db.pdb_proj.domain.costume_review.CostumeReviewService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CostumeRecenziaTest
{

    @Autowired
    private CostumeReviewRepository repository;

    @Autowired
    private CostumeReviewService service;


    @Test
    @Order(1)
    void create_review()
    {
        CostumeReview r = new CostumeReview("Strasne super","Na jednotku",0,0,1,1);
        service.addNewCostumeReview(r);

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

        CostumeReview r = repository.findById(id).get();
        service.updateCostumeReview(id,null,null,1,null,null,null);

        r = repository.findById(id).get();
        System.out.println("HALOOOOOOOOOOOO");
        System.err.println("LIVUUUS");
        System.err.println(r.getLike_reaction());

        assertThat(r.getLike_reaction() == 1).isTrue();
        assertThat(r.getDislike_reaction() == 0).isTrue();

    }

    @Test
    @Order(3)
    void clear_like_to_review()
    {
        Integer id =1;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        CostumeReview r = repository.findById(id).get();
        service.updateCostumeReview(id,null,null,0,null,null,null);

        r = repository.findById(id).get();
        assertThat(r.getLike_reaction() == 0).isTrue();
    }

    @Test
    @Order(4)
    void update_review()
    {
        Integer id =1;
        String popis = "novy popis";

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        CostumeReview r = repository.findById(id).get();
        service.updateCostumeReview(id,null,popis,null,3,null,null);

        r = repository.findById(id).get();
        assertThat(r.getDescription().equals(popis)).isTrue();
        assertThat(r.getDescription().equals("Na jednotku")).isFalse();
    }


    @Test
    @Order(5)
    void delete_review()
    {
        Integer id =1;

        boolean exists = false;
        if(repository.findById(id).isPresent())
            exists = true;

        assertThat(exists).isTrue();

        service.deleteCostumeReview(id);

        exists = false;
        if(repository.findById(id).isPresent())
            exists = true;
        assertThat(exists).isFalse();

    }
}
