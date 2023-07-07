package com.pdb_db.pdb_proj.domainMongo.accessory_wishlist_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccessoryWishlistConfigM {
    @Bean
    CommandLineRunner commandLineRunnerAccessoryWishlistM(
            AccessoryWishlistRepositoryM repository
    ){
        return args -> {
            AccessoryWishlistM Rec4 = new AccessoryWishlistM(1,"Wish1",1,2);
            AccessoryWishlistM Rec5 = new AccessoryWishlistM(2,"Wish2",2,1);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }
}
