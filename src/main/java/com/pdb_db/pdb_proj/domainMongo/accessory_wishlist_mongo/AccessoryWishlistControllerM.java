package com.pdb_db.pdb_proj.domainMongo.accessory_wishlist_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/accessory-wishlistM")
@AllArgsConstructor
public class AccessoryWishlistControllerM {

    private final AccessoryWishlistServiceM accessoryWishlistServiceM;

    @GetMapping
    public List<AccessoryWishlistM> fetchAllAccessoryWishlistsM()
    {
        return accessoryWishlistServiceM.getAllAccessoryWishlistsM();
    }
}
