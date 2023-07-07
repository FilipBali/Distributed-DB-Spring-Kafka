package com.pdb_db.pdb_proj.domain.costume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CostumeRepository costumeRepository;

    @Autowired
    public CostumeService(CostumeRepository costumeRepository) {
        this.costumeRepository = costumeRepository;
    }

    public List<Costume> getCostumes()
    {
        return costumeRepository.findAll();
    }

    public Costume getCostumeById(Integer id)
    {
        Optional <Costume> optionalCostume = costumeRepository.findById(id);
        if (!optionalCostume.isPresent())
        {
            throw new IllegalStateException("This Costume does not exist");
        }
        return optionalCostume.get();
    }

    public Optional<Costume> getCostumeByName(String name)
    {
        Optional <Costume> optionalCostume = costumeRepository.findCostumeByName(name);
        if (!optionalCostume.isPresent())
        {
            throw new IllegalStateException("This costume does not exist");
        }
        return optionalCostume;
    }

    public List<Costume> getCostumesByMaterial(String material)
    {
        return  costumeRepository.findAllByMaterial(material);
    }

    public void addNewCostume(Costume costume)
    {
        Optional<Costume> costumeOptional =  costumeRepository.findCostumeByName(costume.getName());

        //Make sure there is not a costume with a same name
        if(costumeOptional.isPresent())
        {
            throw new IllegalStateException("Costume already exists");
        }

        // Oracle
        costumeRepository.save(costume);
        // Kafka -> MongoDB
        costume.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(costume);
    }

    public void deleteCostume(Integer id)
    {
        boolean exists = costumeRepository.existsById(id);

        if (!exists)
        {
            throw new IllegalStateException("Costume with this ID "+id+" does not exist");
        }

        // Oracle
        costumeRepository.deleteById(id);
        // Kafka -> MongoDB
        Costume costume = new Costume(id);
        costume.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(costume);
    }

    @Transactional
    public void updateCostume(Integer id, String name, String description, String material, String category,
                              Integer size_number, java.util.Date production_date)
    {
        Costume costume = costumeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Costume with ID "+id+"does not exist"));

        if (name != null && name.length() > 0)
        {
            //Nazov cant be shared with two different objects
            Optional<Costume> costumeOptional = costumeRepository.findCostumeByName(name);
            if(costumeOptional.isPresent())
            {
                throw new IllegalStateException("Costume name is taken");
            }
            costume.setName(name);
        }
        if (description != null && description.length() > 0)
        {
            costume.setDescription(description);
        }
        if (material != null && material.length() > 0)
        {
            costume.setMaterial(material);
        }
        if (category != null && category.length() > 0)
        {
            costume.setCategory(category);
        }
        if (size_number!= null )
        {
            costume.setSize_number(size_number);
        }
        if (production_date != null)
        {
            costume.setProduction_date(production_date);
        }

        // Kafka -> MongoDB
        costume.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(costume);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(Costume costume) {
        try {
            String str_costume = OBJECT_MAPPER.writeValueAsString(costume);
            //SOT = Source of truth
            kafkaTemplate.send("costumeService_SOT_event", str_costume);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
