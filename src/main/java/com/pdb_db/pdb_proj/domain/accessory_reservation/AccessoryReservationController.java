package com.pdb_db.pdb_proj.domain.accessory_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/accessory-reservation")
public class AccessoryReservationController
{

    private final AccessoryReservationService accessoryReservationService;

    @Autowired
    public AccessoryReservationController(AccessoryReservationService accessoryReservationService)
    {
        this.accessoryReservationService = accessoryReservationService;
    }

    @GetMapping
    public List<AccessoryReservation> getAccessoryReservations()
    {
        return accessoryReservationService.getAccessoryReservations();
    }

    @PostMapping
    public void registerNewAccessoryReservation(@RequestBody AccessoryReservation accessoryReservation)
    {
        accessoryReservationService.addNewAccessoryReservation(accessoryReservation);
    }

    @DeleteMapping(path = "{id}")
    public void deleteAccessoryReservation(@PathVariable("id") Integer id)
    {
        accessoryReservationService.deleteAccessoryReservation(id);
    }
    @PutMapping(path = "{id}")
    public void updateAccessoryReservation(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false) Integer accessoryId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.util.Date borrowDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.util.Date returnDate,

            @RequestParam(required = false) Integer isReturned)

    {
        accessoryReservationService.updateAccessoryReservation(id, customerId, accessoryId, borrowDate, returnDate,
                isReturned);
    }
}
