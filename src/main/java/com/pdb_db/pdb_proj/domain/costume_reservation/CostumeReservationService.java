package com.pdb_db.pdb_proj.domain.costume_reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import com.pdb_db.pdb_proj.domain.costume.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostumeReservationService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CostumeReservationRepository costumeReservationRepository;

    @Autowired
    public CostumeReservationService(CostumeReservationRepository costumeReservationRepository) {
        this.costumeReservationRepository = costumeReservationRepository;
    }

    public List<CostumeReservation> getCostumeReservations()
    {
        return costumeReservationRepository.findAll();
    }

    public List<CostumeReservation> getAllOngoing()
    {
        return costumeReservationRepository.findAllByIsReturned(0);
    }

    public List<CostumeReservation> getAllEnded()
    {
        return costumeReservationRepository.findAllByIsReturned(1);
    }


    public void addNewCostumeReservation(CostumeReservation costumeReservation)
    {
        //Check user
        Optional<Customer> customerOptional = costumeReservationRepository.
                findCustomerById(costumeReservation.getCustumerId());

        if(!customerOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation to non existent user");
        }

        //Check costume
        Optional<Costume> costumeOptional = costumeReservationRepository.findCostumeById(costumeReservation.getCostumeId());
        if(!costumeOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation for non existent costume");
        }

        // Oracle
        costumeReservationRepository.save(costumeReservation);
        // Kafka -> MongoDB
        costumeReservation.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(costumeReservation);
    }

    public void deleteCostumeReservation(Integer id) {

        CostumeReservation costumeReservation = costumeReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Costume reservation with ID "+id+" does not exist"));

        // Oracle
        costumeReservationRepository.deleteById(id);
        // Kafka -> MongoDB
        costumeReservation.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(costumeReservation);
    }

    @Transactional
    public void updateKostymRezervacia(Integer id,
                                       Integer customerId,
                                       Integer costumeId,
                                       java.util.Date borrowDate,
                                       java.util.Date returnDate,
                                       Integer isReturned
                                       )
    {
        CostumeReservation costumeReservation = costumeReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Costume reservation with ID "+id+" does not exist"));

        if (customerId != null)
        {
            costumeReservation.setCustumerId(customerId);
        }
        if (costumeId != null)
        {
            costumeReservation.setCostumeId(costumeId);
        }
        if (borrowDate != null){
            costumeReservation.setBorrowDate(borrowDate);
        }
        if (returnDate != null){
            costumeReservation.setReturnDate(returnDate);
        }
        if (isReturned != null && (isReturned == 0 || isReturned == 1)) {
            costumeReservation.setIsReturned(isReturned);
        }
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(CostumeReservation costumeReservation) {
        try {
            String str_costumeReservation = OBJECT_MAPPER.writeValueAsString(costumeReservation);
            //SOT = Source of truth
            kafkaTemplate.send("costumeReservationService_SOT_event", str_costumeReservation);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
