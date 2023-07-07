package com.pdb_db.pdb_proj.domain.accessory;

import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;

@Entity
@Table
public class Accessory implements AccessoryInterface {

  @Id
  @SequenceGenerator(
          name = "accessory_sequence",
          sequenceName = "accessory_sequence",
          allocationSize = 1
  )
  @GeneratedValue
          (strategy = GenerationType.SEQUENCE,
          generator = "accessory_sequence"
          )

  private Integer id;
  private String name;
  private String description;
  private String material;
  private String category;
  private java.util.Date production_date;
  @Transient
  private rest_operationType rest_operation;

  public Accessory(){}
  public Accessory(Integer id){
    this.id = id;
  }

  public Accessory(Integer id, String name, String description, String material, String category, java.util.Date production_date)
  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.material = material;
    this.category = category;
    this.production_date = production_date;
  }

  public Accessory(String name, String description, String material, String category, java.sql.Date production_date)
  {
    this.name = name;
    this.description = description;
    this.material = material;
    this.category = category;
    this.production_date = production_date;
  }


  @Override
  public Integer getId() {
    return id;
  }
  @Override
  public void setId(Integer id) {
    this.id = id;
  }


  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String getDescription() {
    return description;
  }
  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getMaterial() { return material; }
  @Override
  public void setMaterial(String material) {
    this.material = material;
  }


  @Override
  public String getCategory() {
    return category;
  }
  @Override
  public void setCategory(String category) {
    this.category = category;
  }


  @Override
  public java.util.Date getProduction_date() {
    return production_date;
  }
  @Override
  public void setProduction_date(java.util.Date production_date) {
    this.production_date = production_date;
  }

  @Override
  public String toString()
  {
      return "Accessory {" +
              "id=" + id+
              "name=" + name + '\'' +
              "description=" + description + '\'' +
              "material=" + material+
              "category=" + category +
              "production_date=" + production_date +" }";
  }

  public rest_operationType getOperation(){ return rest_operation; }
  public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }
}
