package com.pdb_db.pdb_proj.domain.costume_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/costume-review")
public class CostumeReviewController {

    private final CostumeReviewService costumeReviewService;

    @Autowired
    public CostumeReviewController(CostumeReviewService costumeReviewService){
        this.costumeReviewService = costumeReviewService;
    }

    @GetMapping
    public List<CostumeReview> getCostumeReview(){
        return costumeReviewService.getCostumeReview();
    }

    @PostMapping
    public void registerNewCostumeReview(@RequestBody CostumeReview costumeReview){
        costumeReviewService.addNewCostumeReview(costumeReview);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCostumeReview(@PathVariable("id") Integer id)
    {
        costumeReviewService.deleteCostumeReview(id);

    }

    @PutMapping(path = "{id}")
    public void updateCostumeReview(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer like_reaction,
            @RequestParam(required = false) Integer dislike_reaction,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer costumeId
    ){
        costumeReviewService.updateCostumeReview(id, name, description, like_reaction, dislike_reaction, customerId,
                costumeId);
    }

}
