package com.pdb_db.pdb_proj.domain.costume_review;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostumeReviewRepository
        extends JpaRepository<CostumeReview, Integer> {

    @Override
    Optional<CostumeReview> findById(Integer id);

    @Query("SELECT u FROM Customer u where u.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT k FROM Costume k where k.id = ?1")
    Optional<Costume> findCostumeById(Integer id);

}
