package com.pdb_db.pdb_proj.domain.accessory_wishlist;

import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccessoryWishlistRepository extends JpaRepository<AccessoryWishlist, Integer>
{
    @Override
    Optional<AccessoryWishlist> findById(Integer id);

    @Query("SELECT u FROM Customer u where u.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    @Query("SELECT k FROM AccessoryWishlist k where k.name = ?1")
    Optional<AccessoryWishlist> findWishByName(String name);
    @Query("SELECT u FROM Accessory u where u.id = ?1")
    Optional<Accessory> findAccessoryById(Integer id);

    @Query("SELECT k FROM AccessoryWishlist k where k.customerId = ?1")
    List<AccessoryWishlist> findAllAccessoryWishlistsByCustomerId(Integer id);

}
