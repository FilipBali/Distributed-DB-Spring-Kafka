package com.pdb_db.pdb_proj.domain.accessory_reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.customer.CustomerRepository;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryReservationService
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final AccessoryReservationRepository accessoryReservationRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AccessoryReservationService(AccessoryReservationRepository accessoryReservationRepository,
                                       CustomerRepository customerRepository) {
        this.accessoryReservationRepository = accessoryReservationRepository;
        this.customerRepository = customerRepository;
    }

    public List<AccessoryReservation> getAccessoryReservations()
    {
        return accessoryReservationRepository.findAll();
    }

    public List<AccessoryReservation> getAllOngoing()
    {
        return accessoryReservationRepository.findAllByIsReturned(0);
    }

    public List<AccessoryReservation> getAllEnded()
    {
        return accessoryReservationRepository.findAllByIsReturned(1);
    }


    public void addNewAccessoryReservation(AccessoryReservation accessoryReservation)
    {
        //Check user
        boolean customerBool = customerRepository.existsById(accessoryReservation.getCustomerId());

        if(!customerBool)
        {
            throw new IllegalStateException("Can not create accessory reservation to non existent user");
        }

        //Check accessory
        Optional<Accessory> accessoryOptional = accessoryReservationRepository.findAccessoryById(
                accessoryReservation.getAccessoryId()
        );
        if(!accessoryOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation for non existent costume");
        }

        // Oracle
        accessoryReservationRepository.save(accessoryReservation);
        // Kafka -> MongoDB
        accessoryReservation.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(accessoryReservation);

    }

    public void deleteAccessoryReservation(Integer id)
    {
        AccessoryReservation accessoryReservation = accessoryReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Accessory reservation with ID "+id+" does not exist"));

        //Oracle
        accessoryReservationRepository.deleteById(id);
        // Kafka -> MongoDB
        accessoryReservation.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(accessoryReservation);
    }

    @Transactional
    public void updateAccessoryReservation(Integer id,
                                           Integer customerId,
                                           Integer accessoryId,
                                           java.util.Date borrowDate,
                                           java.util.Date returnDate,
                                           Integer isReturned)
    {
        AccessoryReservation accessoryReservation = accessoryReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Accessory reservation with ID "+id+" does not exist"));

        if (customerId != null)
        {
            accessoryReservation.setCustomerId(customerId);
        }
        if (accessoryId != null)
        {
            accessoryReservation.setAccessoryId(accessoryId);
        }
        if (borrowDate != null){
            accessoryReservation.setBorrowDate(borrowDate);
        }
        if (returnDate != null){
            accessoryReservation.setReturnDate(returnDate);
        }
        if (isReturned != null && (isReturned == 0 || isReturned == 1)) {
            accessoryReservation.setIsReturned(isReturned);
        }
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(AccessoryReservation accessoryReservation) {
        try {
            String str_accessoryReservation = OBJECT_MAPPER.writeValueAsString(accessoryReservation);
            //SOT = Source of truth
            kafkaTemplate.send("accessoryReservationService_SOT_event", str_accessoryReservation);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
