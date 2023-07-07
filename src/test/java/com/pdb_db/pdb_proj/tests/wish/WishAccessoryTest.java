package com.pdb_db.pdb_proj.tests.wish;

import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlist;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlistRepository;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlistService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishAccessoryTest
{
    @Autowired
    AccessoryWishlistRepository repository;

    @Autowired
    AccessoryWishlistService service;
    @Test
    @Order(1)
    void create_wish()
    {
        AccessoryWishlist w = new AccessoryWishlist("W",2,2);
        service.addNewWishlist(w);

        boolean exists = false;
        if(repository.findById(w.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }


    @Test
    @Order(2)
    void update_wish()
    {
        String novy_nazov = "novy nazov";
        String stary_nazov = "W";

        AccessoryWishlist w = new AccessoryWishlist();

        if(service.getWishByName(stary_nazov).isPresent())
        {
            w = service.getWishByName(stary_nazov).get();
        }

        service.updateWishlist(w.getId(),novy_nazov,null,null);
        w = service.getWishByName(novy_nazov).get();

        assertThat(w.getName().equals(novy_nazov)).isTrue();
        assertThat(w.getName().equals(stary_nazov)).isFalse();
    }
    @Test
    @Order(3)
    void get_wishes()
    {
        List<AccessoryWishlist> list = service.getWishlist();
        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach( l ->
        {
            if(repository.findWishByName(l.getName()).isPresent())
                exists.set(true);

            assertThat(exists.get()).isTrue();
            exists.set(false);
        });
    }

    @Test
    @Order(4)
    void delete_wish()
    {
        String nazov = "novy nazov";
        Integer id = 0;
        AccessoryWishlist w = new AccessoryWishlist();

        boolean exists = false;

        if(repository.findWishByName(nazov).isPresent())
        {
            exists = true;
            w =repository.findWishByName(nazov).get();
            id = w.getId();
        }
        assertThat(exists).isTrue();

        service.deleteWishlist(id);

        exists = false;
        if(repository.findWishByName(nazov).isPresent())
        {
            exists = true;
        }
        assertThat(exists).isFalse();
    }
}
