package com.pdb_db.pdb_proj.domainMongo.costume_review_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CostumeReviewM
{
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer like_reaction;
    private Integer dislike_reaction;
    private Integer customerId;
    private Integer costumeId;

    public CostumeReviewM(){};
    public CostumeReviewM(Integer id){
        this.id = id;
    };

    public CostumeReviewM(Integer id, String name, String description, Integer like_reaction, Integer dislike_reaction,
                          Integer customerId, Integer costumeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.like_reaction = like_reaction;
        this.dislike_reaction = dislike_reaction;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLike_reaction() {
        return like_reaction;
    }

    public void setLike_reaction(Integer like_reaction) {
        this.like_reaction = like_reaction;
    }

    public Integer getDislike_reaction() {
        return dislike_reaction;
    }

    public void setDislike_reaction(Integer dislike_reaction) {
        this.dislike_reaction = dislike_reaction;
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

    public void setCostumeId(Integer costumeId) {
        this.costumeId = costumeId;
    }

}
