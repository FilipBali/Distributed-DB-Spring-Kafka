package com.pdb_db.pdb_proj.domainMongo.costume_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class CostumeM
{
    @Id
    private Integer id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private String material;
    private String category;
    private Integer size_number;
    private java.util.Date production_number;

    public CostumeM(){};

    public CostumeM(Integer id ){
        this.id = id;
    };

    public CostumeM(Integer id, String name, String description, String material, String category, Integer size_number,
                    java.util.Date production_number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.material = material;
        this.category = category;
        this.size_number = size_number;
        this.production_number = production_number;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSize_number() {
        return size_number;
    }

    public void setSize_number(Integer size_number) {
        this.size_number = size_number;
    }

    public java.util.Date getProduction_number() {
        return production_number;
    }

    public void setProduction_number(java.util.Date production_number) {
        this.production_number = production_number;
    }



}
