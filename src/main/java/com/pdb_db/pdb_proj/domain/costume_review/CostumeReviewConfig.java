package com.pdb_db.pdb_proj.domain.costume_review;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CostumeReviewConfig {

    @Bean
    CommandLineRunner commandLineRunnerCostumeReview(
            CostumeReviewRepository repository
    ){
        return args -> {
            CostumeReview Rec4 = new CostumeReview(1,
                    "Tento kostym je super darcek",
                    "Super kostym",
                    1,
                    2,
                    1,
                    1);
            CostumeReview Rec5 = new CostumeReview(2,
                    "Spokojna s kostymom",
                    "Krasny kostym",
                    10,
                    3,
                    2,
                    2);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }
}
