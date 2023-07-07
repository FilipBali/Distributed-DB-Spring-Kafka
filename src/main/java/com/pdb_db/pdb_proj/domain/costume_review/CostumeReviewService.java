package com.pdb_db.pdb_proj.domain.costume_review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostumeReviewService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CostumeReviewRepository costumeReviewRepository;

    @Autowired
    public CostumeReviewService(CostumeReviewRepository costumeReviewRepository) {
        this.costumeReviewRepository = costumeReviewRepository;
    }


    public List<CostumeReview> getCostumeReview() {
        return costumeReviewRepository.findAll();
    }

    // Add costume review
    public void addNewCostumeReview(CostumeReview costumeReview)
    {
        //Check user
        Optional<Customer> customerOptional = costumeReviewRepository.findCustomerById(costumeReview.getCustomerId());
        if(!customerOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation to non existent user");
        }

        //Check costume
        Optional<Costume> costumeOptional = costumeReviewRepository.findCostumeById(costumeReview.getCostumeId());
        if(!costumeOptional.isPresent())
        {
            throw new IllegalStateException("Can not create costume reservation for non existent costume");
        }

        // Oracle
        costumeReviewRepository.save(costumeReview);
        // Kafka -> MongoDB
        costumeReview.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(costumeReview);

    }

    public void deleteCostumeReview(Integer id) {
        boolean exists = costumeReviewRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Unsuccessful DELETE request. Record with id: "+id+" is NOT exists!");
        }

        // Oracle
        costumeReviewRepository.deleteById(id);
        // Kafka -> MongoDB
        CostumeReview costumeReview = new CostumeReview(id);
        costumeReview.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(costumeReview);
    }

    @Transactional
    public void updateCostumeReview(Integer id, String name, String description, Integer like_reaction,
                                    Integer dislike_reaction, Integer customerId, Integer costumeId) {
        CostumeReview rkR = costumeReviewRepository.findById(id
        ).orElseThrow(() ->
                new IllegalStateException("Unsuccessful UPDATE request. Record with id: "+id+" is NOT exists!"));

        rkR.setId(id);
        if (name != null && name.length() > 0){
            rkR.setName(name);
        }
        if (description != null && description.length() > 0){
            rkR.setDescription(description);
        }
        if (like_reaction != null && like_reaction >= 0){
            rkR.setLike_reaction(like_reaction);
        }
        if (dislike_reaction != null && dislike_reaction >= 0){
            rkR.setDislike_reaction(dislike_reaction);
        }
        if (customerId != null){
            rkR.setCustomerId(customerId);
        }
        if (costumeId != null){
            rkR.setCostumeId(costumeId);
        }

        // Kafka -> MongoDB
        rkR.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(rkR);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(CostumeReview costumeReview) {
        try {
            String str_costumeReview = OBJECT_MAPPER.writeValueAsString(costumeReview);
            //SOT = Source of truth
            kafkaTemplate.send("costumeReviewService_SOT_event", str_costumeReview);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
