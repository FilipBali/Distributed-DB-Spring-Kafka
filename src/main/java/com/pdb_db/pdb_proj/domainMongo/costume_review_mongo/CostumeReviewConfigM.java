package com.pdb_db.pdb_proj.domainMongo.costume_review_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CostumeReviewConfigM {
    @Bean
    CommandLineRunner commandLineRunnerRecenziaKostymM(
            CostumeReviewRepositoryM repository
    ){
        return args -> {
            CostumeReviewM Rec4 = new CostumeReviewM(1,
                    "Tento kostym je super darcek",
                    "Super kostym",
                    1,
                    2,
                    1,
                    1);

            CostumeReviewM Rec5 = new CostumeReviewM(2,
                    "Spokojna s kostymom",
                    "Krasny kostym",
                    10,
                    3,
                    2,
                    2);

            repository.save(Rec4);
            repository.save(Rec5);
        };
    }
}
