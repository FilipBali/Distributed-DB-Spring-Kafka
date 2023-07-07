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
    void create_costume()
    {
        String nazov = "saty";

        Costume k =  new Costume(nazov, "kostym","saten", "zenske", 40, new Date(System.currentTimeMillis()));
        service.addNewCostume(k);

        boolean exists = false;
        if(repository.findCostumeByName(nazov).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void get_all_costumes()
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
    void get_all_costumes_by_material()
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
    void update_costume()
    {
        String nazov = "saty";
        String popis = "novy popis";
        String material = "brokat";

        Costume k = new Costume();
        boolean exist = false;

        if (service.getCostumeByName(nazov).isPresent())
        {
            k = service.getCostumeByName(nazov).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        //Kostym update
        service.updateCostume(k.getId(),null,popis,material,null,34,null);

        //Kostym is updated
        assertThat(service.getCostumeById(k.getId()).getName().equals(nazov)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getMaterial().equals(material)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getDescription().equals(popis)).isTrue();
        assertThat(service.getCostumeById(k.getId()).getSize_number().equals(40)).isFalse();
    }

    @Test
    @Order(5)
    void get_concrete_costume()
    {
        String nazov = "saty";
        Costume k = new Costume();

        //Kostym exists by name
        boolean exists = false;
        if(repository.findCostumeByName(nazov).isPresent())
        {
            exists = true;
            k = repository.findCostumeByName(nazov).get();
        }
        assertThat(exists).isTrue();

        //Kostym exists by id
        exists = false;
        if(repository.findById(k.getId()).isPresent())
            exists = true;

        assertThat(exists).isTrue();

    }

    @Test
    @Order(6)
    void delete_costume()
    {
        String nazov = "saty";
        boolean exist = false;

        //Kostym still exists
        Costume k = new Costume();

        if (service.getCostumeByName(nazov).isPresent())
        {
            k = service.getCostumeByName(nazov).get();
            exist = true;
        }
        assertThat(exist).isTrue();

        Integer id = k.getId();

        //Delete kostym
        service.deleteCostume(k.getId());

        //Kostym no longer exists
        exist = false;
        if(repository.findCostumeByName(nazov).isPresent())
            exist = true;
        assertThat(exist).isFalse();
    }
}
