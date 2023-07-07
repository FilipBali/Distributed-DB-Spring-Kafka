package com.pdb_db.pdb_proj.domainMongo.accessory_review_mongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessoryReviewConfigM
{

   @Bean
    CommandLineRunner commandLineRunnerAccessoryReviewM(
            AccessoryReviewRepositoryM repository
    ){
       return args -> {
           AccessoryReviewM Rec4 = new AccessoryReviewM(1,
                   "Spokojny s doplnkom",
                   "Super",
                   1,
                   0,
                   1,
                   1);
           AccessoryReviewM Rec5 = new AccessoryReviewM(2,
                   "Vyborny doplnok",
                   "Krasne",
                   4,
                   1,
                   2,
                   2);

           repository.save(Rec4);
           repository.save(Rec5);
       };
    }
}
