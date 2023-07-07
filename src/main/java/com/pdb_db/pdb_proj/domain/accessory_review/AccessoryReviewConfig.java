package com.pdb_db.pdb_proj.domain.accessory_review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccessoryReviewConfig {

    @Bean
    CommandLineRunner commandLineRunnerAccessoryReview(
            AccessoryReviewRepository repository
    ){
        return args -> {
            AccessoryReview Rec4 = new AccessoryReview(1,
                    "Spokojny s doplnkom",
                    "Super",
                    1,
                    0,
                    1,
                    1);

            AccessoryReview Rec5 = new AccessoryReview(2,
                    "Vyborny doplnok",
                    "Krasne",
                    4,
                    1,
                    2,
                    2);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }

}
