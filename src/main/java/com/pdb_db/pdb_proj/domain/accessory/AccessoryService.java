package com.pdb_db.pdb_proj.domain.accessory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final AccessoryRepository accessoryRepository;

    @Autowired
    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public List<Accessory> getAccessories()
    {
        return accessoryRepository.findAll();
    }


    public List<Accessory> getAccessoriesByMaterial(String material)
    {
        return  accessoryRepository.findAllByMaterial(material);
    }

    public Optional<Accessory> getAccessoryById(Integer id)
    {
        return accessoryRepository.findById(id);
    }

    public Optional<Accessory> getAccessoryByName(String name)
    {
        return accessoryRepository.findAccessoryByName(name);
    }


    public void addNewAccessory(Accessory accessory)
    {
        Optional<Accessory> AccessoryOptional =  accessoryRepository.findAccessoryByName(accessory.getName());

        //Make sure there is not a costume with a same name
        if(AccessoryOptional.isPresent())
        {
            throw new IllegalStateException("Accessory already exists");
        }

        // Oracle
        accessoryRepository.save(accessory);

        // Kafka -> MongoDB
        accessory.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(accessory);
    }

    public void deleteAccessory(Integer accessoryId)
    {
        boolean exists = accessoryRepository.existsById(accessoryId);

        if (!exists)
        {
            throw new IllegalStateException("Accessory with this ID "+accessoryId+" does not exist");
        }

        // Oracle
        accessoryRepository.deleteById(accessoryId);

        // Kafka -> MongoDB
        Accessory accessory = new Accessory(accessoryId);
        accessory.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(accessory);
    }

    @Transactional
    public void updateAccessory(Integer accessoryId, String name, String description, String material,
                                String category, java.util.Date production_date)
    {
        Accessory accessory = accessoryRepository.findById(accessoryId)
            .orElseThrow(() -> new IllegalStateException("Accessory with ID "+accessoryId+"does not exist"));

        if (name != null && name.length() > 0

        )
        {
           //Name cant be shared with two different objects
            Optional<Accessory> accessoryOptional = accessoryRepository.findAccessoryByName(name);
            if(accessoryOptional.isPresent())
            {
                throw new IllegalStateException("Name of accessory taken");
            }
            accessory.setName(name);
        }
        if (description != null && description.length() > 0)
        {
            accessory.setDescription(description);
        }
        if (material != null && material.length() > 0)
        {
            accessory.setMaterial(material);
        }
        if (category != null && category.length() > 0)
        {
            accessory.setCategory(category);
        }
        if (production_date != null)
        {
            accessory.setProduction_date(production_date);
        }

        // Kafka -> MongoDB
        accessory.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(accessory);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(Accessory accessory) {
        try {
            String str_accessory = OBJECT_MAPPER.writeValueAsString(accessory);
            //SOT = Source of truth
            kafkaTemplate.send("accessoryService_SOT_event", str_accessory);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
