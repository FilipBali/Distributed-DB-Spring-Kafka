package com.pdb_db.pdb_proj.domainMongo.costume_wishlist_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CostumeWishlistM {

    @Id
    private Integer id;
    private String name;
    private Integer customerId;
    private Integer costumeId;

    public CostumeWishlistM(){}
    public CostumeWishlistM(Integer id){
        this.id = id;
    }
    public CostumeWishlistM(Integer id, String name, Integer customerId, Integer costumeId){
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.costumeId = costumeId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    public Integer getCostumeId() {
        return costumeId;
    }


    public void setCostumeId(Integer costumeId)
    {
        this.costumeId = costumeId;
    }

}
