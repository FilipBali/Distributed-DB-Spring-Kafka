package com.pdb_db.pdb_proj.domainMongo.costume_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/costumeM")
@AllArgsConstructor
public class CostumeControllerM
{
    private final CostumeServiceM costumeServiceM;

    @GetMapping
    public List<CostumeM> fetchAllCostumesM()
    {
        return costumeServiceM.getAllCostumesM();
    }

}
