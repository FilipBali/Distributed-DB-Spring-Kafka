package com.pdb_db.pdb_proj.tests.sortiment;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.costume.CostumeRepository;
import com.pdb_db.pdb_proj.domain.costume.CostumeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class CostumeTest
{
    @Autowired
    private CostumeRepository repository;

    @Autowired
    private CostumeService service;

    @Test
    @Order(1)
    void createCostume()
    {
        String name = "saty";

        Costume k =  new Costume(
                name,
                "kostym",
                "saten",
                "zenske",
                40,
                new Date(System.currentTimeMillis()));
        service.addNewCostume(k);

        boolean exists = false;
        if(repository.findCostumeByName(name).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void getAllCostumes()
    {
        List<Costume> list = service.getCostumes();
        AtomicBoolean exists = new AtomicBoolean(false);

        list.forEach(l ->
        {
            if(repository.findCostumeByName(l.getName()).isPresent())
                exists.set(true);
            assertThat(exists.get()).isTrue();
            exists.set(false);
        });
    }

    @Test
    @Order(3)
    void getAllCostumesByMaterial()
    {
        String material = "saten";

        List<Costume> list = service.getCostumesByMaterial(material);

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
    void updateCostume()
    {
        String name = "saty";
        String description = "novy popis";
        String material = "brokat";

        Costume k = new Costume();
        boolean exist = false;

        if (service.getCostumeByName(name).isPresent())
        {
            k = service.getCostumeByName(name).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Costume update
        service.updateCostume(k.getId(),null,description,material,null,34,null);

        //Costume is updated
        assertThat(service.getCostumeById(k.getId()).getName().equals(name)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getMaterial().equals(material)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getDescription().equals(description)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getSize_number().equals(40)).isFalse();
    }

    @Test
    @Order(5)
    void getCostume()
    {
        String name = "saty";
        Costume k = new Costume();

        //Costume exists by name
        boolean exists = false;
        if(repository.findCostumeByName(name).isPresent())
        {
            exists = true;
            k = repository.findCostumeByName(name).get();
        }
        assertThat(exists).isTrue();

        //Costume exists by id
        exists = false;
        if(repository.findById(k.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();

    }

    @Test
    @Order(6)
    void deleteCostume()
    {
        String name = "saty";
        boolean exist = false;

        //Costume still exists
        Costume k = new Costume();

        if (service.getCostumeByName(name).isPresent())
        {
            k = service.getCostumeByName(name).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Delete costume
        service.deleteCostume(k.getId());

        //Costume no longer exists
        exist = false;
        if(repository.findCostumeByName(name).isPresent())
            exist = true;
        assertThat(exist).isFalse();
    }
}
