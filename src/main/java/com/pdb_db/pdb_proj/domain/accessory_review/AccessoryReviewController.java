package com.pdb_db.pdb_proj.domain.accessory_review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accessory-review")
public class AccessoryReviewController {

    private final AccessoryReviewService accessoryReviewService;

    @Autowired
    public AccessoryReviewController(AccessoryReviewService accessoryReviewService){
        this.accessoryReviewService = accessoryReviewService;
    }

    @GetMapping
    public List<AccessoryReview> getAccessoryReview(){
        return accessoryReviewService.getAccessoryReview();
    }

    @PostMapping
    public void registerNewAccessoryReview(@RequestBody AccessoryReview accessoryReview){
        accessoryReviewService.addNewAccessoryReview(accessoryReview);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAccessoryReview(@PathVariable("id") Integer id){
        accessoryReviewService.deleteAccessoryReview(id);
    }

    @PutMapping(path = "{id}")
    public void updateAccessoryReview(
        @PathVariable("id") Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) Integer like_reaction,
        @RequestParam(required = false) Integer dislike_reaction,
        @RequestParam(required = false) Integer customerId,
        @RequestParam(required = false) Integer accessoryId
    ){
        accessoryReviewService.updateAccessoryReview(id, name, description, like_reaction, dislike_reaction, customerId,
                accessoryId);
    }

}
