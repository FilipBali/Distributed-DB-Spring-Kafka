package com.pdb_db.pdb_proj.domainMongo.costume_review_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/costume-reviewM")
@AllArgsConstructor
public class CostumeReviewControllerM
{
    private final CostumeReviewServiceM costumeReviewServiceM;

    @GetMapping
    public List<CostumeReviewM> fetchAllRecenziaKostymM()
    {
        return costumeReviewServiceM.getAllCostumeReviewsM();
    }
}
