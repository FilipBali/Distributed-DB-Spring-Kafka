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
    void createAccessory()
    {
        String name = "hreben";

        Accessory d = new Accessory(
                name,
                "jeden",
                "plast",
                "male",
                new Date(System.currentTimeMillis()));
        repository.save(d);

        boolean exists = false;
        if(repository.findAccessoryByName(name).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void noAccessoryWithName()
    {
        String name = "aaa";

        boolean exists = false;
        if(repository.findAccessoryByName(name).isPresent())
            exists = true;

        assertThat(exists).isFalse();
    }

    @Test
    void accessoryByMaterial()
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
