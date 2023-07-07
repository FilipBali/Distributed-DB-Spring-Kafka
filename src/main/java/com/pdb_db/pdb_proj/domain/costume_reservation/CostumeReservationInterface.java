package com.pdb_db.pdb_proj.domain.costume_reservation;

public interface CostumeReservationInterface {

    Integer getId();

    void setId(Integer id);


    Integer getCustumerId();

    void setCustumerId(Integer custumerId);


    Integer getCostumeId();

    void setCostumeId(Integer costumeId);


    java.util.Date getBorrowDate();

    void setBorrowDate(java.util.Date borrowDate);

    java.util.Date getReturnDate();

    void setReturnDate(java.util.Date returnDate);

    Integer getIsReturned();

    void setIsReturned(Integer isReturned);
}
