package com.pdb_db.pdb_proj.tests.sortiment;

import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.accessory.AccessoryRepository;
import com.pdb_db.pdb_proj.domain.accessory.AccessoryService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccessoryTest
{

    @Autowired
    private AccessoryRepository repository;

    @Autowired
    private AccessoryService service;

    @Test
    @Order(1)
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
    @Order(2)
    void getAllAccessories()
    {
        List<Accessory> list = service.getAccessories();
        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach(l ->
        {
            if(repository.findAccessoryByName(l.getName()).isPresent())
                exists.set(true);
            assertThat(exists.get()).isTrue();
            exists.set(false);
        });
    }

    @Test
    @Order(3)
    void getAllAccessoriesByMaterial()
    {
        String material = "plast";

        List<Accessory> list = service.getAccessoriesByMaterial(material);

        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach(l ->
        {
            if(l.getMaterial().equals(material))
                exists.set(true);
            assertThat(exists.get()).isTrue();
            exists.set(false);
        });
    }

    @Test
    @Order(4)
    void update_doplnok()
    {
        String name = "hreben";
        String description = "novy popis";
        String material = "drevo";

        Accessory d = new Accessory();
        boolean exist = false;

        if (service.getAccessoryByName(name).isPresent())
        {
            d = service.getAccessoryByName(name).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Accessory  update
        service.updateAccessory(d.getId(),null,description,material,null,null);

        //Accessory is updated
        assertThat(service.getAccessoryById(d.getId()).get().getName().equals(name)).isTrue();
        assertThat(service.getAccessoryById(d.getId()).get().getMaterial().equals(material)).isTrue();
        assertThat(service.getAccessoryById(d.getId()).get().getDescription().equals(description)).isTrue();
    }


    @Test
    @Order(5)
    void getAccessory()
    {
        String name = "hreben";
        Accessory d = new Accessory();

        //Accessory exists by name
        boolean exists = false;
        if(repository.findAccessoryByName(name).isPresent())
        {
            exists = true;
            d = repository.findAccessoryByName(name).get();
        }
        assertThat(exists).isTrue();

        //Accessory exists by id
        exists = false;
        if(repository.findById(d.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(6)
    void deleteAccessory()
    {
        String nazov = "hreben";
        boolean exist = false;

        //Accessory still exists
        Accessory d = new Accessory();

        if (service.getAccessoryByName(nazov).isPresent())
        {
            d = service.getAccessoryByName(nazov).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Delete accessory
        service.deleteAccessory(d.getId());

        //Accessory no longer exists
        exist = false;
        if(repository.findAccessoryByName(nazov).isPresent())
            exist = true;
        assertThat(exist).isFalse();
    }

}
