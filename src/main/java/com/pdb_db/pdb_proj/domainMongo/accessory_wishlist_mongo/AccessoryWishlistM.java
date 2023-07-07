package com.pdb_db.pdb_proj.domainMongo.accessory_wishlist_mongo;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessoryWishlistM {
    @Id
    private Integer id;
    private String name;
    private Integer customerId;
    private Integer accessoryId;

    public AccessoryWishlistM(){}
    public AccessoryWishlistM(Integer id){
        this.id = id;
    }
    public AccessoryWishlistM(Integer id, String name, Integer customerId, Integer accessoryId){
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.accessoryId = accessoryId;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public Integer getAccessoryId() {
        return accessoryId;
    }

}
