package com.pdb_db.pdb_proj.domain.costume_review;

public interface CostumeReviewInterface {

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


    Integer getCostumeId();

    void setCostumeId(Integer costumeId);


}
