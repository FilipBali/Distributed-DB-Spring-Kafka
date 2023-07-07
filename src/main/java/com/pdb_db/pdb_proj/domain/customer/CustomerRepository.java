package com.pdb_db.pdb_proj.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository
    extends JpaRepository<Customer, Integer> {

    @Override
    Optional<Customer> findById(Integer id);

    Optional<Customer> findCustomerByEmail(String email);

}
