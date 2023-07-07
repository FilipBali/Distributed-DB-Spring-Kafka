package com.pdb_db.pdb_proj.domain.costume_wishlist;


import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;

@Entity
@Table
public class CostumeWishlist implements CostumeWishlistInterface {

  @Id
  @SequenceGenerator(
          name = "costumewishlist_sequence",
          sequenceName = "costumewishlist_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "costumewishlist_sequence"
  )
  private Integer id;
  private String name;
  private Integer customerId;

  private Integer costumeId;

  // CRUD operation
  @Transient
  private rest_operationType rest_operation;


  public CostumeWishlist(){}
  public CostumeWishlist(Integer id){
    this.id = id;
  }
  public CostumeWishlist(String name, Integer customerId, Integer costumeId)
  {
    this.costumeId = costumeId;
    this.name = name;
    this.customerId = customerId;
  }
  public CostumeWishlist(Integer id, String name, Integer customerId, Integer costumeId)
  {
    this.id = id;
    this.costumeId = costumeId;
    this.name = name;
    this.customerId = customerId;
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
  public Integer getCustomerId() {
    return customerId;
  }
  @Override
  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  @Override
  public Integer getCostumeId() {
    return costumeId;
  }

  @Override
  public void setCostumeId(Integer costumeId)
  {
      this.costumeId = costumeId;
  }

  public rest_operationType getOperation(){ return rest_operation; }
  public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }

}
