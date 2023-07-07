package com.pdb_db.pdb_proj.domainMongo.costume_wishlist_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/costume-wishlistM")
@AllArgsConstructor
public class CostumeWishlistControllerM {

    private final CostumeWishlistServiceM costumeWishlistServiceM;

    @GetMapping
    public List<CostumeWishlistM> fetchAllCostumeWishlistsM(){
        return costumeWishlistServiceM.getAllCostumeWishlistsM();
    }

}
