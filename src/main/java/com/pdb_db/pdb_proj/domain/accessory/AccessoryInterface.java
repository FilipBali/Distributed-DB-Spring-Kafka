package com.pdb_db.pdb_proj.domain.accessory;

public interface AccessoryInterface {

    Integer getId();

    void setId(Integer id);


    String getName();

    void setName(String name);


    String getDescription();

    void setDescription(String description);


    String getMaterial();

    void setMaterial(String material);


    String getCategory();

    void setCategory(String category);


    java.util.Date getProduction_date();

    void setProduction_date(java.util.Date production_date);

}
