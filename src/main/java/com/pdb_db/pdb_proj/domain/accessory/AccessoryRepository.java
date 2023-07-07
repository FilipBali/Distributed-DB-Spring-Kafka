package com.pdb_db.pdb_proj.domain.accessory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccessoryRepository extends JpaRepository<Accessory,Integer>
{
    @Query("SELECT d FROM Accessory d where d.name = ?1")
    Optional<Accessory> findAccessoryByName(String name);

    List<Accessory> findAllByMaterial(String material);
}
