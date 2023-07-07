package com.pdb_db.pdb_proj.domainMongo.costume_mongo;

import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CostumeServiceM
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CostumeRepositoryM costumeRepositoryM;
    public List<CostumeM> getAllCostumesM()
    {
        return costumeRepositoryM.findAll();
    }

    @KafkaListener(topics = "costumeService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            Costume costume = OBJECT_MAPPER.readValue(message, Costume.class);

            switch (costume.getOperation()){
                case PUT -> {
                    //Get costume (oracleDB) id
                    Integer id = costume.getId();

                    //Find object by costume (oracleDB) id in mongoDB
                    CostumeM costumeM = costumeRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. " +
                                    "Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    costumeM.setName(costume.getName());
                    costumeM.setDescription(costume.getDescription());
                    costumeM.setMaterial(costume.getMaterial());
                    costumeM.setCategory(costume.getCategory());
                    costumeM.setSize_number(costume.getSize_number());
                    costumeM.setProduction_number(costume.getProduction_date());

                    // Save mongoDB object
                    costumeRepositoryM.save(costumeM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    CostumeM costumeM = new CostumeM(
                            costume.getId(),
                            costume.getName(),
                            costume.getDescription(),
                            costume.getMaterial(),
                            costume.getCategory(),
                            costume.getSize_number(),
                            costume.getProduction_date()
                    );
                    // Save object to MongoDB
                    costumeRepositoryM.save(costumeM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    CostumeM costumeM = new CostumeM(
                            costume.getId()
                    );
                    // Delete object in MongoDB
                    costumeRepositoryM.delete(costumeM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }

}
