package com.pdb_db.pdb_proj.domain.costume_reservation;

import com.pdb_db.pdb_proj.utilities.rest_operationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class CostumeReservation implements CostumeReservationInterface {
  @Id
  @SequenceGenerator(
          name = "costumereservation_sequence",
          sequenceName = "costumereservation_sequence",
          allocationSize = 1
  )
  @GeneratedValue
          (strategy = GenerationType.SEQUENCE,
                  generator = "costumereservation_sequence"
          )
  private Integer id;
  private Integer custumerId;
  private Integer costumeId;
  private java.util.Date borrowDate;
  private java.util.Date returnDate;
  private Integer isReturned;
  @Transient
  private rest_operationType rest_operation;

  public CostumeReservation(){}
  public CostumeReservation(Integer id){
    this.id = id;
  }

  public CostumeReservation(Integer id,
                            Integer custumerId,
                            Integer costumeId,
                            java.util.Date borrowDate,
                            java.util.Date returnDate,
                            Integer isReturned)
  {
    this.id = id;
    this.custumerId = custumerId;
    this.costumeId = costumeId;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.isReturned = isReturned;
  }

  public CostumeReservation(Integer custumerId, Integer costumeId, Date borrowDate, Date returnDate, Integer isReturned) {
    this.custumerId = custumerId;
    this.costumeId = costumeId;
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
  public Integer getCustumerId() {
    return custumerId;
  }
  @Override
  public void setCustumerId(Integer custumerId) {
    this.custumerId = custumerId;
  }


  @Override
  public Integer getCostumeId() {
    return costumeId;
  }
  @Override
  public void setCostumeId(Integer costumeId) {
    this.costumeId = costumeId;
  }


  @Override
  public String toString()
  {
    return "CostumeReservation {" +
            "id=" + id+
            "custumerId=" + custumerId + '\'' +
            "costumeId=" + costumeId +'}';
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
