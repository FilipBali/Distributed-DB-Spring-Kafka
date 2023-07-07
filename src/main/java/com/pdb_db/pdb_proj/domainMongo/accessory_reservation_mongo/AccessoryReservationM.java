package com.pdb_db.pdb_proj.domainMongo.accessory_reservation_mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class AccessoryReservationM {

    @Id
    private Integer id;
    private Integer customerId;
    private Integer accessoryId;


    private java.util.Date borrowDate;
    private java.util.Date returnDate;
    private Integer isReturned;

    public AccessoryReservationM(){}
    public AccessoryReservationM(Integer id){
        this.id = id;
    }


    public AccessoryReservationM(Integer id,
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



    public Integer getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }


    public String toString()
    {
        return "AccessoryReservation {" +
                "id=" + id+
                "customerId=" + customerId + '\'' +
                "accessoryId=" + accessoryId +" }";
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
