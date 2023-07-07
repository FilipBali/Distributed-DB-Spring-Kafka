package com.pdb_db.pdb_proj.domainMongo.costume_reservation_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservation;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CostumeReservationServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CostumeReservationRepositoryM costumeReservationRepositoryM;

    public List<CostumeReservationM> getAllCostumeReservationsM() {
        return costumeReservationRepositoryM.findAll();
    }

    @KafkaListener(topics = "costumeReservationService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            CostumeReservation costumeReservation = OBJECT_MAPPER.readValue(message, CostumeReservation.class);

            switch (costumeReservation.getOperation()){
                case PUT -> {
                    //Get costume reservation (oracleDB) id
                    Integer id = costumeReservation.getId();

                    //Find object by costume review (oracleDB) id in mongoDB
                    CostumeReservationM costumeReservationM = costumeReservationRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    costumeReservationM.setId(costumeReservation.getId());
                    costumeReservationM.setCustomerId(costumeReservation.getCustumerId());
                    costumeReservationM.setCostumeId(costumeReservation.getCostumeId());
                    costumeReservationM.setBorrowDate(costumeReservation.getBorrowDate());
                    costumeReservationM.setReturnDate(costumeReservation.getReturnDate());
                    costumeReservationM.setIsReturned(costumeReservation.getIsReturned());

                    // Save mongoDB object
                    costumeReservationRepositoryM.save(costumeReservationM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    CostumeReservationM costumeReservationM = new CostumeReservationM(
                            costumeReservation.getId(),
                            costumeReservation.getCustumerId(),
                            costumeReservation.getCostumeId(),
                            costumeReservation.getBorrowDate(),
                            costumeReservation.getReturnDate(),
                            costumeReservation.getIsReturned()
                    );
                    // Save object to MongoDB
                    costumeReservationRepositoryM.save(costumeReservationM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    CostumeReservationM costumeReservationM = new CostumeReservationM(
                            costumeReservation.getId()
                    );
                    // Delete object in MongoDB
                    costumeReservationRepositoryM.delete(costumeReservationM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }

}
