package com.pdb_db.pdb_proj.domain.costume_wishlist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CostumeWishlistConfig {

    @Bean
    CommandLineRunner commandLineRunnerCostumeWishlist(
            CostumeWishlistRepository repository
    ){
        return args -> {
            CostumeWishlist Rec4 = new CostumeWishlist(1,"Wish1",1,2);
            CostumeWishlist Rec5 = new CostumeWishlist(2,"Wish2",2,1);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }

}
