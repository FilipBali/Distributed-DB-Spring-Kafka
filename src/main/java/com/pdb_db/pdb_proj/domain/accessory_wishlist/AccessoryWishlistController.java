package com.pdb_db.pdb_proj.domain.accessory_wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accessory-wishlist")
public class AccessoryWishlistController
{
    private final AccessoryWishlistService accessoryWishlistService;

    @Autowired
    public AccessoryWishlistController(AccessoryWishlistService accessoryWishlistService) {
        this.accessoryWishlistService = accessoryWishlistService;
    }

    @GetMapping
    public List<AccessoryWishlist> getWishlist(){
        return accessoryWishlistService.getWishlist();
    }

    @PostMapping
    public void registerNewWishlist(@RequestBody AccessoryWishlist accessoryWishlist){
        accessoryWishlistService.addNewWishlist(accessoryWishlist);
    }

    @DeleteMapping(path = "{id}")
    public void deleteWishlist(@PathVariable("id") Integer id){
        accessoryWishlistService.deleteWishlist(id);
    }

    @PutMapping(path = "{id}")
    public void updateWishlist(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer accessoryId
            ){
        accessoryWishlistService.updateWishlist(id, name, customerId, accessoryId);
    }
}
