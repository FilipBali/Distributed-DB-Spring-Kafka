package com.pdb_db.pdb_proj.domain.accessory_review;

public interface AccessoryReviewInterface {

    Integer getId();

    void setId(Integer id);


    String getName();

    void setName(String name);


    String getDescription();

    void setDescription(String description);


    Integer getLike_reaction();

    void setLike_reaction(Integer like_reaction);


    Integer getDislike_reaction();

    void setDislike_reaction(Integer dislike_reaction);


    Integer getCustomerId();

    void setCustomerId(Integer customerId);


    Integer getAccessoryId();

    void setAccessoryId(Integer accessoryId);

}
