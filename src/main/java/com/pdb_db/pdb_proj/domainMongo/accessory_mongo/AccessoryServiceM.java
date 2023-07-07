package com.pdb_db.pdb_proj.domainMongo.accessory_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccessoryServiceM {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final AccessoryRepositoryM accessoryRepositoryM;

    public List<AccessoryM> getAllAccessoriesM()
    {
        return accessoryRepositoryM.findAll();
    }

    @KafkaListener(topics = "accessoryService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            Accessory accessory = OBJECT_MAPPER.readValue(message, Accessory.class);

            switch (accessory.getOperation()){
                case PUT -> {
                    //Get accessory (oracleDB) id
                    Integer id = accessory.getId();

                    //Find object by accessory (oracleDB) id in mongoDB
                    AccessoryM accessoryM = accessoryRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    accessoryM.setName(accessory.getName());
                    accessoryM.setDescription(accessory.getDescription());
                    accessoryM.setMaterial(accessory.getMaterial());
                    accessoryM.setCategory(accessory.getCategory());
                    accessoryM.setProduction_date(accessory.getProduction_date());

                    // Save mongoDB object
                    accessoryRepositoryM.save(accessoryM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    AccessoryM accessoryM = new AccessoryM(
                            accessory.getId(),
                            accessory.getName(),
                            accessory.getDescription(),
                            accessory.getMaterial(),
                            accessory.getCategory(),
                            accessory.getProduction_date()
                    );
                    // Save object to MongoDB
                    accessoryRepositoryM.save(accessoryM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    AccessoryM accessoryM = new AccessoryM(
                            accessory.getId()
                    );
                    // Delete object in MongoDB
                    accessoryRepositoryM.delete(accessoryM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }
}
