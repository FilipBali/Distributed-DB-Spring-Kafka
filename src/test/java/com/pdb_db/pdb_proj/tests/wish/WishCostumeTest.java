package com.pdb_db.pdb_proj.tests.wish;

import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlist;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlistRepository;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishCostumeTest
{

    @Autowired
    CostumeWishlistRepository repository;

    @Autowired
    CostumeWishlistService service;

    @Test
    @Order(1)
    void createWish()
    {
        CostumeWishlist w = new CostumeWishlist("W",1,2);
        service.addNewCostumeWishlist(w);

        boolean exists = false;
        if(repository.findById(w.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }


    @Test
    @Order(2)
    void updateWish()
    {
        String new_name = "novy nazov";
        String old_name = "W";

        CostumeWishlist w = new CostumeWishlist();

        if(service.getCostumeWishlistByName(old_name).isPresent())
        {
            w = service.getCostumeWishlistByName(old_name).get();
        }

        service.updateWishlist(w.getId(),new_name,null,null);
        w = service.getCostumeWishlistByName(new_name).get();

        assertThat(w.getName().equals(new_name)).isTrue();
        assertThat(w.getName().equals(old_name)).isFalse();
    }
    @Test
    @Order(3)
    void getWishes()
    {
        List<CostumeWishlist> list = service.getCostumeWishlist();
        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach( l ->
        {
            if(repository.findCostumeWishlistByName(l.getName()).isPresent())
                exists.set(true);

            assertThat(exists.get()).isTrue();
            exists.set(false);
        });
    }

    @Test
    @Order(4)
    void deleteWish()
    {
        String name = "novy nazov";
        Integer id = 0;
        CostumeWishlist w = new CostumeWishlist();

        boolean exists = false;

        if(repository.findCostumeWishlistByName(name).isPresent())
        {
            exists = true;
            w =repository.findCostumeWishlistByName(name).get();
            id = w.getId();
        }
        assertThat(exists).isTrue();

        service.deleteCostumeWishlist(id);

        exists = false;
        if(repository.findCostumeWishlistByName(name).isPresent())
        {
            exists = true;
        }
        assertThat(exists).isFalse();
    }
}
