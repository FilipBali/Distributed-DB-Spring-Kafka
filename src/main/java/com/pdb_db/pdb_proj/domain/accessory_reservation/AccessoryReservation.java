package com.pdb_db.pdb_proj.domain.accessory_reservation;

import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;

@Entity
@Table
public class AccessoryReservation implements AccessoryReservationInterface {
  @Id
  @SequenceGenerator(
          name = "accessoryreservation_sequence",
          sequenceName = "accessoryreservation_sequence",
          allocationSize = 1
  )
  @GeneratedValue
          (strategy = GenerationType.SEQUENCE,
                  generator = "accessoryreservation_sequence"
          )

  private Integer id;
  private Integer customerId;
  private Integer accessoryId;
  private java.util.Date borrowDate;
  private java.util.Date returnDate;
  private Integer isReturned;

  @Transient
  private rest_operationType rest_operation;

  public AccessoryReservation(){}
  public AccessoryReservation(Integer id){
    this.id = id;
  }

  public AccessoryReservation(Integer customerId,
                              Integer accessoryId,
                              java.util.Date borrowDate,
                              java.util.Date returnDate,
                              Integer isReturned)
  {
    this.customerId = customerId;
    this.accessoryId = accessoryId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.isReturned = isReturned;
  }

  public AccessoryReservation(Integer id,
                              Integer customerId,
                              Integer accessoryId,
                              java.util.Date borrowDate,
                              java.util.Date returnDate,
                              Integer isReturned)
  {
    this.id = id;
    this.customerId = customerId;
    this.accessoryId = accessoryId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.isReturned = isReturned;
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

  @Override
  public String toString()
  {
    return "AccessoryReservation {" +
            "id=" + id+
            "customerId=" + customerId + '\'' +
            "accessoryId=" + accessoryId +" }";
  }

  public rest_operationType getOperation(){ return rest_operation; }
  public void setOperation(rest_operationType rest_operation){ this.rest_operation = rest_operation; }

  @Override
  public java.util.Date getBorrowDate() {
    return borrowDate;
  }
  @Override
  public void setBorrowDate(java.util.Date borrowDate) {
    this.borrowDate = borrowDate;
  }


  @Override
  public java.util.Date getReturnDate() {
    return returnDate;
  }
  @Override
  public void setReturnDate(java.util.Date returnDate) {
    this.returnDate = returnDate;
  }


  @Override
  public Integer getIsReturned() {
    return isReturned;
  }
  @Override
  public void setIsReturned(Integer isReturned) {
    this.isReturned = isReturned;
  }
}
