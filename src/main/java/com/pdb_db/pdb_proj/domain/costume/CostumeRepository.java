package com.pdb_db.pdb_proj.domain.costume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumeRepository extends JpaRepository<Costume,Integer>
{
    @Query("SELECT k FROM Costume k where k.name = ?1")
    Optional<Costume> findCostumeByName(String name);
    List<Costume> findAllByMaterial(String material);
}
