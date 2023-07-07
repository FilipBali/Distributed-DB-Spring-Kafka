package com.pdb_db.pdb_proj.domain.costume_wishlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.costume.Costume;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostumeWishlistService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CostumeWishlistRepository costumeWishlistRepository;

    @Autowired
    public CostumeWishlistService(CostumeWishlistRepository costumeWishlistRepository) {
        this.costumeWishlistRepository = costumeWishlistRepository;
    }

    public List<CostumeWishlist> getCostumeWishlist() {
        return costumeWishlistRepository.findAll();
    }

    public Optional<CostumeWishlist> getCostumeWishlistByName(String name)
    {
        return costumeWishlistRepository.findCostumeWishlistByName(name);
    }


    // Costume wishlist creation
    public void addNewCostumeWishlist(CostumeWishlist costumeWishlist)
    {
       Optional<Customer> customerOptional = costumeWishlistRepository.
               findCustomerById(costumeWishlist.getCustomerId());

        if(!customerOptional.isPresent())
        {
            throw new IllegalStateException("Can not create wishlist to non existent user");
        }

        // Oracle
        costumeWishlistRepository.save(costumeWishlist);
        // Kafka -> MongoDB
        costumeWishlist.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(costumeWishlist);
    }


    public void deleteCostumeWishlist(Integer id) {
        boolean exists = costumeWishlistRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Unsuccessful DELETE request. Record with id: "+id+" is NOT exists!");
        }

        // Oracle
        costumeWishlistRepository.deleteById(id);
        // Kafka -> MongoDB
        CostumeWishlist costumeWishlist = new CostumeWishlist(id);
        costumeWishlist.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(costumeWishlist);
    }

    //Operacia: Uprava wishlistu
    @Transactional
    public void updateWishlist(Integer id, String name, Integer customerId, Integer costumeId) {
        CostumeWishlist wR = costumeWishlistRepository.findById(id
        ).orElseThrow(() ->
                new IllegalStateException("Unsuccessful UPDATE request. Record with id: "+id+" is NOT exists!"));

        wR.setId(id);
        if (name != null && name.length() > 0){
            wR.setName(name);
        }
        if (customerId != null){
            wR.setCustomerId(customerId);
        }
        if (costumeId != null){
            wR.setCostumeId(costumeId);
        }

        // Kafka -> MongoDB
        wR.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(wR);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(CostumeWishlist costumeWishlist) {
        try {
            String str_costumeWishlist = OBJECT_MAPPER.writeValueAsString(costumeWishlist);
            //SOT = Source of truth
            kafkaTemplate.send("costumeWishlistService_SOT_event", str_costumeWishlist);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
