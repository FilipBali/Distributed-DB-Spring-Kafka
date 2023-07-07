package com.pdb_db.pdb_proj.domain.costume_reservation;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumeReservationRepository extends JpaRepository<CostumeReservation,Integer>
{
    @Query("SELECT u FROM Customer u where u.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT k FROM Costume k where k.id = ?1")
    Optional<Costume> findCostumeById(Integer id);

    List<CostumeReservation> findAllByIsReturned(Integer isReturned);

    @Query("SELECT k FROM CostumeReservation k where k.custumerId = ?1")
    List<CostumeReservation> findAllCostumeReservationsByCustomerId(Integer id);

}
