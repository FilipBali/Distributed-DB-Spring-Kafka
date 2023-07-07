package com.pdb_db.pdb_proj.domainMongo.customer_mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfigM
{
    @Bean
    CommandLineRunner commandLineRunnerCustomerM(
            CustomerRepositoryM customerRepositoryM
    ){
        return args -> {
            CustomerM Rec4 = new CustomerM(
                    1,
                    "Meno1",
                    "Priezvisko1",
                    "email@email.com",
                    "+421123123132",
                    "Slovensko",
                    "Bánska Bystrica",
                    "Ulica SNP",
                    12,
                    94945);
            CustomerM Rec5 = new CustomerM(
                    2,
                    "Meno2",
                    "Priezvisko2",
                    "email2@email.com",
                    "+421789789798",
                    "Slovensko",
                    "Trenčín",
                    "Ulica ulice",
                    57,
                    92214);


            customerRepositoryM.save(Rec4);
            customerRepositoryM.save(Rec5);
        };
    }
}
