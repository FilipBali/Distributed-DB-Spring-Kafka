package com.pdb_db.pdb_proj.domain.accessory_reservation;

import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessoryReservationRepository extends JpaRepository<AccessoryReservation,Integer>
{
    @Query("SELECT u FROM Customer u where u.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT k FROM Accessory k where k.id = ?1")
    Optional<Accessory> findAccessoryById(Integer id);

    List<AccessoryReservation> findAllByIsReturned(Integer isReturned);

    @Query("SELECT k FROM AccessoryReservation k where k.customerId = ?1")
    List<AccessoryReservation> findAllAccessoryReservationsByCustomerId(Integer id);
}
