package com.pdb_db.pdb_proj.domainMongo.accessory_reservation_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservation;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccessoryReservationServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final AccessoryReservationRepositoryM accessoryReservationRepositoryM;

    public List<AccessoryReservationM> getAllAccessoryReservationsM() {
        return accessoryReservationRepositoryM.findAll();
    }

    @KafkaListener(topics = "accessoryReservationService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            AccessoryReservation accessoryReservation = OBJECT_MAPPER.readValue(message, AccessoryReservation.class);

            switch (accessoryReservation.getOperation()){
                case PUT -> {
                    //Get accessory reservation (oracleDB) id
                    Integer id = accessoryReservation.getId();

                    //Find object by accessory reservation (oracleDB) id in mongoDB
                    AccessoryReservationM accessoryReservationM = accessoryReservationRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    accessoryReservationM.setId(accessoryReservation.getId());
                    accessoryReservationM.setCustomerId(accessoryReservation.getCustomerId());
                    accessoryReservationM.setAccessoryId(accessoryReservation.getAccessoryId());
                    accessoryReservationM.setBorrowDate(accessoryReservation.getBorrowDate());
                    accessoryReservationM.setReturnDate(accessoryReservation.getReturnDate());
                    accessoryReservationM.setIsReturned(accessoryReservation.getIsReturned());

                    // Save mongoDB object
                    accessoryReservationRepositoryM.save(accessoryReservationM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    AccessoryReservationM accessoryReservationM = new AccessoryReservationM(
                            accessoryReservation.getId(),
                            accessoryReservation.getCustomerId(),
                            accessoryReservation.getAccessoryId(),
                            accessoryReservation.getBorrowDate(),
                            accessoryReservation.getReturnDate(),
                            accessoryReservation.getIsReturned()
                    );
                    // Save object to MongoDB
                    accessoryReservationRepositoryM.save(accessoryReservationM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    AccessoryReservationM accessoryReservationM = new AccessoryReservationM(
                            accessoryReservation.getId()
                    );
                    // Delete object in MongoDB
                    accessoryReservationRepositoryM.delete(accessoryReservationM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
