package com.pdb_db.pdb_proj.domainMongo.costume_reservation_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/costume-reservationM")
@AllArgsConstructor
public class CostumeReservationControllerM {

    private final CostumeReservationServiceM costumeReservationServiceM;

    @GetMapping
    public List<CostumeReservationM> fetchAllCostumeReservationsM()
    {
        return costumeReservationServiceM.getAllCostumeReservationsM();
    }
}
