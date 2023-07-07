package com.pdb_db.pdb_proj.domainMongo.costume_wishlist_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlist;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class CostumeWishlistServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public final CostumeWishlistRepositoryM costumeWishlistRepositoryM;

    public List<CostumeWishlistM> getAllCostumeWishlistsM(){
        return costumeWishlistRepositoryM.findAll();
    }


    @KafkaListener(topics = "costumeWishlistService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            CostumeWishlist costumeWishlist = OBJECT_MAPPER.readValue(message, CostumeWishlist.class);

            switch (costumeWishlist.getOperation()){
                case PUT -> {
                    //Get costume wishlist (oracleDB) id
                    Integer id = costumeWishlist.getId();

                    //Find object by costume wishlist (oracleDB) id in mongoDB
                    CostumeWishlistM costumeWishlistM = costumeWishlistRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    costumeWishlistM.setId(costumeWishlist.getId());
                    costumeWishlistM.setName(costumeWishlist.getName());
                    costumeWishlistM.setCustomerId(costumeWishlist.getCustomerId());
                    costumeWishlistM.setCostumeId(costumeWishlist.getCostumeId());

                    // Save mongoDB object
                    costumeWishlistRepositoryM.save(costumeWishlistM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    CostumeWishlistM costumeWishlistM = new CostumeWishlistM(
                            costumeWishlist.getId(),
                            costumeWishlist.getName(),
                            costumeWishlist.getCustomerId(),
                            costumeWishlist.getCostumeId()
                    );
                    // Save object to MongoDB
                    costumeWishlistRepositoryM.save(costumeWishlistM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    CostumeWishlistM costumeWishlistM = new CostumeWishlistM(
                            costumeWishlist.getId()
                    );
                    // Delete object in MongoDB
                    costumeWishlistRepositoryM.delete(costumeWishlistM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
