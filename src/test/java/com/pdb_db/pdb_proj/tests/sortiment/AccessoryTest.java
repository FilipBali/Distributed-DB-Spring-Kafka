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
    void create_accessory()
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
    @Order(2)
    void get_all_accessories()
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
    void get_all_accessories_by_material()
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
        String nazov = "hreben";
        String popis = "novy popis";
        String material = "drevo";

        Accessory d = new Accessory();
        boolean exist = false;

        if (service.getAccessoryByName(nazov).isPresent())
        {
            d = service.getAccessoryByName(nazov).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Doplnok  update
        service.updateAccessory(d.getId(),null,popis,material,null,null);

        //Doplnok is updated
        assertThat(service.getAccessoryById(d.getId()).get().getName().equals(nazov)).isTrue();
        assertThat(service.getAccessoryById(d.getId()).get().getMaterial().equals(material)).isTrue();
        assertThat(service.getAccessoryById(d.getId()).get().getDescription().equals(popis)).isTrue();
    }


    @Test
    @Order(5)
    void get_concrete_accessory()
    {
        String nazov = "hreben";
        Accessory d = new Accessory();

        //Doplnok exists by name
        boolean exists = false;
        if(repository.findAccessoryByName(nazov).isPresent())
        {
            exists = true;
            d = repository.findAccessoryByName(nazov).get();
        }
        assertThat(exists).isTrue();

        //Doplnok exists by id
        exists = false;
        if(repository.findById(d.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(6)
    void delete_accessory()
    {
        String nazov = "hreben";
        boolean exist = false;

        //Doplnok still exists
        Accessory d = new Accessory();

        if (service.getAccessoryByName(nazov).isPresent())
        {
            d = service.getAccessoryByName(nazov).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        Integer id = d.getId();

        //Delete kostym
        service.deleteAccessory(d.getId());

        //Kostym no longer exists
        exist = false;
        if(repository.findAccessoryByName(nazov).isPresent())
            exist = true;
        assertThat(exist).isFalse();
    }

}
