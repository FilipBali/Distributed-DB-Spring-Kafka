package com.pdb_db.pdb_proj.domainMongo.accessory_review_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/accessory-reviewM")
@AllArgsConstructor
public class AccessoryReviewControllerM
{
    private final AccessoryReviewServiceM accessoryReviewServiceM;

    @GetMapping
    public List<AccessoryReviewM> fetchAllAccessoryReviewsM()
    {
        return accessoryReviewServiceM.getAllAccessoryReviewsM();
    }
}
