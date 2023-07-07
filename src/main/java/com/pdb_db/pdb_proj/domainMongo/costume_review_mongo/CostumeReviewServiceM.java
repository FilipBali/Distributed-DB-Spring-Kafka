package com.pdb_db.pdb_proj.domainMongo.costume_review_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.costume_review.CostumeReview;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CostumeReviewServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CostumeReviewRepositoryM costumeReviewRepositoryM;
    public List<CostumeReviewM> getAllCostumeReviewsM() {
        return costumeReviewRepositoryM.findAll();
    }

    @KafkaListener(topics = "costumeReviewService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            CostumeReview costumeReview = OBJECT_MAPPER.readValue(message, CostumeReview.class);

            switch (costumeReview.getOperation()){
                case PUT -> {
                    //Get costume review (oracleDB) id
                    Integer id = costumeReview.getId();

                    //Find object by costumer review (oracleDB) id in mongoDB
                    CostumeReviewM costumeReviewM = costumeReviewRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    costumeReviewM.setName(costumeReview.getName());
                    costumeReviewM.setDescription(costumeReview.getDescription());
                    costumeReviewM.setLike_reaction(costumeReview.getLike_reaction());
                    costumeReviewM.setDislike_reaction(costumeReview.getDislike_reaction());
                    costumeReviewM.setCustomerId(costumeReview.getCustomerId());
                    costumeReviewM.setCostumeId(costumeReview.getCostumeId());

                    // Save mongoDB object
                    costumeReviewRepositoryM.save(costumeReviewM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    CostumeReviewM costumeReviewM = new CostumeReviewM(
                            costumeReview.getId(),
                            costumeReview.getName(),
                            costumeReview.getDescription(),
                            costumeReview.getLike_reaction(),
                            costumeReview.getDislike_reaction(),
                            costumeReview.getCustomerId(),
                            costumeReview.getCostumeId()
                    );
                    // Save object to MongoDB
                    costumeReviewRepositoryM.save(costumeReviewM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    CostumeReviewM costumeReviewM = new CostumeReviewM(
                            costumeReview.getId()
                    );
                    // Delete object in MongoDB
                    costumeReviewRepositoryM.delete(costumeReviewM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
