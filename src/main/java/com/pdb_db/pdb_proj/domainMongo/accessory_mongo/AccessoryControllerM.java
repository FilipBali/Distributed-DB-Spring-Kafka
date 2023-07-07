package com.pdb_db.pdb_proj.domainMongo.accessory_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/accessoryM")
@AllArgsConstructor
public class AccessoryControllerM {

    private final AccessoryServiceM accessoryServiceM;

    @GetMapping
    public List<AccessoryM> fetchAllAccessoriesM()
    {
        return accessoryServiceM.getAllAccessoriesM();
    }

}
