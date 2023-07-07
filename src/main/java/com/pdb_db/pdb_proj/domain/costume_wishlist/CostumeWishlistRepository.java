package com.pdb_db.pdb_proj.domain.costume_wishlist;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CostumeWishlistRepository
    extends JpaRepository<CostumeWishlist, Integer> {

    @Override
    Optional<CostumeWishlist> findById(Integer id);

    @Query("SELECT k FROM CostumeWishlist k where k.name = ?1")
    Optional<CostumeWishlist> findCostumeWishlistByName(String name);

    @Query("SELECT u FROM Customer u where u.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT u FROM Costume u where u.id = ?1")
    Optional<Costume> findCostumeById(Integer id);

    @Query("SELECT k FROM CostumeWishlist k where k.customerId = ?1")
    List<CostumeWishlist> findAllCostumeWishlistByCustomerId(Integer id);


}
