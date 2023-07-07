package com.pdb_db.pdb_proj.domainMongo.accessory_review_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessoryReviewM
{
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer like_reaction;
    private Integer dislike_reaction;
    private Integer customerId;
    private Integer accessoryId;

    public AccessoryReviewM(){};
    public AccessoryReviewM(Integer id){
        this.id = id;
    };

    public AccessoryReviewM(Integer id, String name, String description, Integer like_reaction,
                            Integer dislike_reaction, Integer customerId, Integer accessoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.like_reaction = like_reaction;
        this.dislike_reaction = dislike_reaction;
        this.customerId = customerId;
        this.accessoryId = accessoryId;
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


    public Integer getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

}
