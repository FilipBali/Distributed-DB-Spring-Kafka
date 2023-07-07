package com.pdb_db.pdb_proj.tests.sortiment;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.costume.CostumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CostumeRepositoryTest
{
    @Autowired
    private CostumeRepository repository;

    @Test
    void create_kostym()
    {
        String nazov = "saty4";

        Costume k =  new Costume(nazov, "kostym","saten", "zenske", 40, new Date(System.currentTimeMillis()));
        repository.save(k);

        boolean exists = false;
        if(repository.findCostumeByName(nazov).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void no_kostym_with_name()
    {
        String nazov = "hreben";

        boolean exists = false;
        if(repository.findCostumeByName(nazov).isPresent())
            exists = true;

        assertThat(exists).isFalse();
    }

    @Test
    void kostymy_by_material()
    {
        String material = "saten";

        List<Costume> list = repository.findAllByMaterial(material);
        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach(
                l->
                {
                    if(l.getMaterial().equals(material))
                        exists.set(true);
                    assertThat(exists.get()).isTrue();
                    exists.set(false);
                }
        );
    }
}
