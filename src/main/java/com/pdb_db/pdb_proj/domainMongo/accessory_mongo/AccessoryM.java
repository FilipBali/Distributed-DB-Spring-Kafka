package com.pdb_db.pdb_proj.domainMongo.accessory_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessoryM
{
    @Id
    private Integer id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private String material;
    private String category;
    private java.util.Date production_date;

    public AccessoryM(){}
    public AccessoryM(Integer id){
        this.id = id;
    }
    public AccessoryM(Integer id, String name, String description, String material, String category,
                      java.util.Date production_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.material = material;
        this.category = category;
        this.production_date = production_date;
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

    public  java.util.Date getProduction_date() {
        return production_date;
    }

    public void setProduction_date(java.util.Date production_date) {
        this.production_date = production_date;
    }


}
