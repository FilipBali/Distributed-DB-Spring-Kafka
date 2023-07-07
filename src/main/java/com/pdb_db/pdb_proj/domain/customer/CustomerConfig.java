package com.pdb_db.pdb_proj.domain.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunnerCustomer(
            CustomerRepository repository
    ){
        return args -> {
            Customer Rec4 = new Customer(1,
                    "Meno1",
                    "Priezvisko1",
                    "email@email.com",
                    "+421123123132",
                    "Slovensko",
                    "Bánska Bystrica",
                    "Ulica SNP",
                    12,
                    94945);
            Customer Rec5 = new Customer(2,
                    "Meno2",
                    "Priezvisko2",
                    "email2@email.com",
                    "+421789789798",
                    "Slovensko",
                    "Trenčín",
                    "Ulica ulice",
                    57,
                    92214);

            repository.saveAll(List.of(Rec4, Rec5));
        };
    }

}
