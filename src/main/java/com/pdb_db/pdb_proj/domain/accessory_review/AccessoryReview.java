package com.pdb_db.pdb_proj.domain.accessory_review;

import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;

@Entity
@Table
public class AccessoryReview implements AccessoryReviewInterface {

  @Id
  @SequenceGenerator(
          name = "accessoryreview_sequence",
          sequenceName = "accessoryreview_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "accessoryreview_sequence"
  )
  private Integer id;
  private String name;
  private String description;
  private Integer like_reaction;
  private Integer dislike_reaction;
  private Integer customerId;
  private Integer accessoryId;

  public AccessoryReview(String name, String description, Integer like_reaction, Integer dislike_reaction,
                         Integer customerId, Integer accessoryId) {
    this.name = name;
    this.description = description;
    this.like_reaction = like_reaction;
    this.dislike_reaction = dislike_reaction;
    this.customerId = customerId;
    this.accessoryId = accessoryId;
  }

  public AccessoryReview(){}
  public AccessoryReview(Integer id){
    this.id = id;
  }
  public AccessoryReview(Integer id,
                         String name,
                         String description,
                         Integer like_reaction,
                         Integer dislike_reaction,
                         Integer customerId,
                         Integer accessoryId){
    this.id = id;
    this.name = name;
    this.description = description;
    this.like_reaction = like_reaction;
    this.dislike_reaction = dislike_reaction;
    this.customerId = customerId;
    this.accessoryId = accessoryId;
  }

  public AccessoryReview(Integer id,
                         String name,
                         String description,
                         Integer like_reaction,
                         Integer dislike_reaction){
    this.id = id;
    this.name = name;
    this.description = description;
    this.like_reaction = like_reaction;
    this.dislike_reaction = dislike_reaction;
  }

  public AccessoryReview(String name,
                         String description,
                         Integer like_reaction,
                         Integer dislike_reaction){
    this.name = name;
    this.description = description;
    this.like_reaction = like_reaction;
    this.dislike_reaction = dislike_reaction;

  }

  @Transient
  private rest_operationType rest_operation;


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
  public Integer getLike_reaction() {
    return like_reaction;
  }
  @Override
  public void setLike_reaction(Integer like_reaction) {
    this.like_reaction = like_reaction;
  }


  @Override
  public Integer getDislike_reaction() {
    return dislike_reaction;
  }
  @Override
  public void setDislike_reaction(Integer dislike_reaction) {
    this.dislike_reaction = dislike_reaction;
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
  public Integer getAccessoryId() {
    return accessoryId;
  }
  @Override
  public void setAccessoryId(Integer accessoryId) {
    this.accessoryId = accessoryId;
  }

  public rest_operationType getOperation(){ return rest_operation; }
  public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }

}
