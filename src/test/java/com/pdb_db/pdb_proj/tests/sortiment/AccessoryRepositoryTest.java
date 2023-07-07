package com.pdb_db.pdb_proj.tests.sortiment;

import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.accessory.AccessoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccessoryRepositoryTest
{
    @Autowired
    private AccessoryRepository repository;

    @Test
    void create_doplnok()
    {
        String nazov = "hreben";

        Accessory d = new Accessory(nazov,"jeden","plast","male",new Date(System.currentTimeMillis()));
        repository.save(d);

        boolean exists = false;
        if(repository.findAccessoryByName(nazov).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void no_doplnok_with_name()
    {
        String nazov = "aaa";

        boolean exists = false;
        if(repository.findAccessoryByName(nazov).isPresent())
            exists = true;

        assertThat(exists).isFalse();
    }

    @Test
    void doplnok_by_material()
    {
        String material = "saten";

        List<Accessory> list = repository.findAllByMaterial(material);
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
