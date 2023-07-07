package com.pdb_db.pdb_proj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class PdbProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdbProjApplication.class, args);
    }
}
