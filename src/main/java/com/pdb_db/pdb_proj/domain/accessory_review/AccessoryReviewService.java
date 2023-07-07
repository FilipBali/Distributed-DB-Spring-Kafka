package com.pdb_db.pdb_proj.domain.accessory_review;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryReviewService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final AccessoryReviewRepository accessoryReviewRepository;

    @Autowired
    public AccessoryReviewService(AccessoryReviewRepository accessoryReviewRepository) {
        this.accessoryReviewRepository = accessoryReviewRepository;
    }


    public List<AccessoryReview> getAccessoryReview() {
        return accessoryReviewRepository.findAll();
    }


    public void addNewAccessoryReview(AccessoryReview accessoryReview) {

        //Check user
        Optional<Customer> customerOptional = accessoryReviewRepository.
                findCustomerById(accessoryReview.getCustomerId());
        if(!customerOptional.isPresent())
        {
            throw new IllegalStateException("Can not create accessory reservation to non existent user");
        }

        //Check accessory
        Optional<Accessory> accessoryOptional = accessoryReviewRepository.
                findAccessoryById(accessoryReview.getAccessoryId());
        if(!accessoryOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation for non existent costume");
        }

        // Oracle
        accessoryReviewRepository.save(accessoryReview);
        // Kafka -> MongoDB
        accessoryReview.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(accessoryReview);
    }

    public void deleteAccessoryReview(Integer id) {
        boolean exists = accessoryReviewRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Unsuccessful DELETE request. Record with id: "+id+" is NOT exists!");
        }

        // Oracle
        accessoryReviewRepository.deleteById(id);
        // Kafka -> MongoDB
        AccessoryReview accessoryReview = new AccessoryReview(id);
        accessoryReview.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(accessoryReview);
    }

    @Transactional
    public void updateAccessoryReview(Integer id, String name, String description, Integer like_reaction,
                                      Integer dislike_reaction, Integer customerId, Integer accessoryId) {
        AccessoryReview rdR = accessoryReviewRepository.findById(id
        ).orElseThrow(() ->
                new IllegalStateException("Unsuccessful UPDATE request. Record with id: "+id+" is NOT exists!"));

        rdR.setId(id);
        if (name != null && name.length() > 0){
            rdR.setName(name);
        }
        if (description != null && description.length() > 0){
            rdR.setDescription(description);
        }
        if (like_reaction != null && like_reaction >= 0){
            rdR.setLike_reaction(like_reaction);
        }
        if (dislike_reaction != null && dislike_reaction >= 0){
            rdR.setDislike_reaction(dislike_reaction);
        }
        if (customerId != null){
            rdR.setCustomerId(customerId);
        }
        if (accessoryId != null){
            rdR.setAccessoryId(accessoryId);
        }

        // Kafka -> MongoDB
        rdR.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(rdR);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(AccessoryReview accessoryReview) {
        try {
            String str_accessoryReview = OBJECT_MAPPER.writeValueAsString(accessoryReview);
            //SOT = Source of truth
            kafkaTemplate.send("accessoryReviewService_SOT_event", str_accessoryReview);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
