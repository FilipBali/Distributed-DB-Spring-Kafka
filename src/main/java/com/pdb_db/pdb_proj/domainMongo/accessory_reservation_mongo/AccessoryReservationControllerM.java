package com.pdb_db.pdb_proj.domainMongo.accessory_reservation_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("api/v1/accessory-reservationM")
@AllArgsConstructor
public class AccessoryReservationControllerM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final AccessoryReservationRepositoryM accessoryReservationRepositoryM;

    @GetMapping
    public List<AccessoryReservationM> fetchAllAccessoryReservationsM()
    {
        return accessoryReservationRepositoryM.findAll();
    }

}
