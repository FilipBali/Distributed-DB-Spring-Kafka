package com.pdb_db.pdb_proj.domain.accessory_reservation;

public interface AccessoryReservationInterface {

    Integer getId();

    void setId(Integer id);


    Integer getCustomerId();

    void setCustomerId(Integer customerId);


    Integer getAccessoryId();

    void setAccessoryId(Integer accessoryId);


    java.util.Date getBorrowDate();

    void setBorrowDate(java.util.Date borrowDate);

    java.util.Date getReturnDate();

    void setReturnDate(java.util.Date returnDate);

    Integer getIsReturned();

    void setIsReturned(Integer isReturned);
}
