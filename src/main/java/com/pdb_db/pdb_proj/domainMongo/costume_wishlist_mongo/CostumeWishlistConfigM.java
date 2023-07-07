package com.pdb_db.pdb_proj.domainMongo.costume_wishlist_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CostumeWishlistConfigM {

    @Bean
    CommandLineRunner commandLineRunnerCostumeWishlistM(
            CostumeWishlistRepositoryM repository
    ){
        return args -> {
            CostumeWishlistM Rec4 = new CostumeWishlistM(1,"Wish1",1,2);
            CostumeWishlistM Rec5 = new CostumeWishlistM(2,"Wish2",2,1);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }
}
