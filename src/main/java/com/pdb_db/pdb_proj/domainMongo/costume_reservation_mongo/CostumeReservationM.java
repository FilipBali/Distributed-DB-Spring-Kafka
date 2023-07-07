package com.pdb_db.pdb_proj.domainMongo.costume_reservation_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CostumeReservationM {

    @Id
    private Integer id;
    private Integer customerId;
    private Integer costumeId;
    private java.util.Date borrowDate;
    private java.util.Date returnDate;
    private Integer isReturned;

    public CostumeReservationM(){}
    public CostumeReservationM(Integer id){
        this.id = id;
    }


    public CostumeReservationM(Integer id,
                               Integer customerId,
                               Integer costumeId,
                               java.util.Date borrowDate,
                               java.util.Date returnDate,
                               Integer isReturned)
    {
        this.id = id;
        this.customerId = customerId;
        this.costumeId = costumeId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }



    public Integer getCostumeId() {
        return costumeId;
    }

    public void setCostumeId(Integer costumeId) {
        this.costumeId = costumeId;
    }


    public String toString()
    {
        return "CostumeReservation {" +
                "id=" + id+
                "customerId=" + customerId + '\'' +
                "costumeId=" + costumeId +'}';
    }


    public java.util.Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(java.util.Date borrowDate) {
        this.borrowDate = borrowDate;
    }



    public java.util.Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(java.util.Date returnDate) {
        this.returnDate = returnDate;
    }



    public Integer getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Integer isReturned) {
        this.isReturned = isReturned;
    }

}


