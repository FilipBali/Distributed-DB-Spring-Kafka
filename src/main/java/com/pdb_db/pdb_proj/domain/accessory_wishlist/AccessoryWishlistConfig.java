package com.pdb_db.pdb_proj.domain.accessory_wishlist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccessoryWishlistConfig
{
    @Bean
    CommandLineRunner commandLineRunnerAccessoryWishlist(
            AccessoryWishlistRepository repository
    ){
        return args -> {
            AccessoryWishlist Rec4 = new AccessoryWishlist(1,"Wish1",1,2);
            AccessoryWishlist Rec5 = new AccessoryWishlist(2,"Wish2",2,1);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }
}
