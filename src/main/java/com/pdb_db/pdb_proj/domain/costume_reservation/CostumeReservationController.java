package com.pdb_db.pdb_proj.domain.costume_reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/costume-reservation")
public class CostumeReservationController
{
    private final CostumeReservationService costumeReservationService;

    @Autowired
    public CostumeReservationController(CostumeReservationService costumeReservationService) {
        this.costumeReservationService = costumeReservationService;
    }

    @GetMapping
    public List<CostumeReservation> getCostumeReservations()
    {
        return costumeReservationService.getCostumeReservations();
    }

    @PostMapping
    public void registerNewCostumeReservation(@RequestBody CostumeReservation costumeReservation)
    {
        costumeReservationService.addNewCostumeReservation(costumeReservation);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCostumeReservation(@PathVariable("id") Integer id)
    {
        costumeReservationService.deleteCostumeReservation(id);
    }

    @PutMapping(path = "{id}")
    public void updateCostumeReservation(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) Integer customerId,
            @RequestParam(required = false)Integer costumeId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.util.Date borrowDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.util.Date returnDate,

            @RequestParam(required = false) Integer isReturned)
    {
        costumeReservationService.updateKostymRezervacia(id, customerId, costumeId, borrowDate, returnDate, isReturned);
    }
}
