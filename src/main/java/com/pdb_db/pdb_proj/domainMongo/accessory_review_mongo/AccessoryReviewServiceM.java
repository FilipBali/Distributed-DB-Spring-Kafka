package com.pdb_db.pdb_proj.domainMongo.accessory_review_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory_review.AccessoryReview;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class AccessoryReviewServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final AccessoryReviewRepositoryM accessoryReviewRepositoryM;
    public List<AccessoryReviewM> getAllAccessoryReviewsM() {
        return  accessoryReviewRepositoryM.findAll();
    }

    @KafkaListener(topics = "accessoryReviewService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            AccessoryReview accessoryReview = OBJECT_MAPPER.readValue(message, AccessoryReview.class);

            switch (accessoryReview.getOperation()){
                case PUT -> {
                    //Get accessory review (oracleDB) id
                    Integer id = accessoryReview.getId();

                    //Find object by accessory review (oracleDB) id in mongoDB
                    AccessoryReviewM accessoryReviewM = accessoryReviewRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    accessoryReviewM.setName(accessoryReview.getName());
                    accessoryReviewM.setDescription(accessoryReview.getDescription());
                    accessoryReviewM.setLike_reaction(accessoryReview.getLike_reaction());
                    accessoryReviewM.setDislike_reaction(accessoryReview.getDislike_reaction());
                    accessoryReviewM.setCustomerId(accessoryReview.getCustomerId());
                    accessoryReviewM.setAccessoryId(accessoryReview.getAccessoryId());

                    // Save mongoDB object
                    accessoryReviewRepositoryM.save(accessoryReviewM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    AccessoryReviewM accessoryReviewM = new AccessoryReviewM(
                            accessoryReview.getId(),
                            accessoryReview.getName(),
                            accessoryReview.getDescription(),
                            accessoryReview.getLike_reaction(),
                            accessoryReview.getDislike_reaction(),
                            accessoryReview.getCustomerId(),
                            accessoryReview.getAccessoryId()
                    );
                    // Save object to MongoDB
                    accessoryReviewRepositoryM.save(accessoryReviewM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    AccessoryReviewM accessoryReviewM = new AccessoryReviewM(
                            accessoryReview.getId()
                    );
                    // Delete object in MongoDB
                    accessoryReviewRepositoryM.delete(accessoryReviewM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
