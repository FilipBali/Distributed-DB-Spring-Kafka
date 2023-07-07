package com.pdb_db.pdb_proj.domain.costume_wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/costume-wishlist")
public class CostumeWishlistController {

    private final CostumeWishlistService costumeWishlistService;

    @Autowired
    public CostumeWishlistController(CostumeWishlistService costumeWishlistService) {
        this.costumeWishlistService = costumeWishlistService;
    }

    @GetMapping
    public List<CostumeWishlist> getCostumeWishlist(){
        return costumeWishlistService.getCostumeWishlist();
    }

    @PostMapping
    public void registerNewCostumeWishlist(@RequestBody CostumeWishlist costumeWishlist){
        costumeWishlistService.addNewCostumeWishlist(costumeWishlist);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCostumeWishlist(@PathVariable("id") Integer id){
        costumeWishlistService.deleteCostumeWishlist(id);
    }

    @PutMapping(path = "{id}")
    public void updateCostumeWishlist(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer costumeId
    ){
        costumeWishlistService.updateWishlist(id, name, customerId, costumeId);
    }

}
