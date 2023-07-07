package com.pdb_db.pdb_proj.domainMongo.accessory_wishlist_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlist;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccessoryWishlistServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public final AccessoryWishlistRepositoryM accessoryWishlistRepositoryM;

    public List<AccessoryWishlistM> getAllAccessoryWishlistsM(){
        return accessoryWishlistRepositoryM.findAll();
    }


    @KafkaListener(topics = "accessoryWishlistService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            AccessoryWishlist accessoryWishlist = OBJECT_MAPPER.readValue(message, AccessoryWishlist.class);

            switch (accessoryWishlist.getOperation()){
                case PUT -> {
                    //Get accessory wishlist (oracleDB) id
                    Integer id = accessoryWishlist.getId();

                    //Find object by accessory wishlist (oracleDB) id in mongoDB
                    AccessoryWishlistM accessoryWishlistM = accessoryWishlistRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    accessoryWishlistM.setId(accessoryWishlist.getId());
                    accessoryWishlistM.setName(accessoryWishlist.getName());
                    accessoryWishlistM.setCustomerId(accessoryWishlist.getCustomerId());
                    accessoryWishlistM.setAccessoryId(accessoryWishlist.getAccessoryId());

                    // Save mongoDB object
                    accessoryWishlistRepositoryM.save(accessoryWishlistM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    AccessoryWishlistM accessoryWishlistM = new AccessoryWishlistM(
                            accessoryWishlist.getId(),
                            accessoryWishlist.getName(),
                            accessoryWishlist.getCustomerId(),
                            accessoryWishlist.getAccessoryId()
                    );
                    // Save object to MongoDB
                    accessoryWishlistRepositoryM.save(accessoryWishlistM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    AccessoryWishlistM accessoryWishlistM = new AccessoryWishlistM(
                            accessoryWishlist.getId()
                    );
                    // Delete object in MongoDB
                    accessoryWishlistRepositoryM.delete(accessoryWishlistM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
