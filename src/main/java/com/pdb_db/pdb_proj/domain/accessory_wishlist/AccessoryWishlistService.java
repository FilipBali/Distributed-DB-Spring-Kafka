package com.pdb_db.pdb_proj.domain.accessory_wishlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory.Accessory;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccessoryWishlistService
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final AccessoryWishlistRepository accessoryWishlistRepository;

    @Autowired
    public AccessoryWishlistService(AccessoryWishlistRepository accessoryWishlistRepository) {
        this.accessoryWishlistRepository = accessoryWishlistRepository;
    }

    public List<AccessoryWishlist> getWishlist() {
        return accessoryWishlistRepository.findAll();
    }

    public Optional<AccessoryWishlist> getWishByName(String name)
    {
        return accessoryWishlistRepository.findWishByName(name);
    }

    // Wishlist creation
    public void addNewWishlist(AccessoryWishlist accessoryWishlist)
    {
        Optional<Customer> customerOptional = accessoryWishlistRepository.findCustomerById(accessoryWishlist.getCustomerId());
        if(!customerOptional.isPresent())
        {
            throw new IllegalStateException("Can not create wishlist to non existent user");
        }

        Optional<Accessory> AccessoryOptional = accessoryWishlistRepository.
                findAccessoryById(accessoryWishlist.getAccessoryId());

        // Oracle
        accessoryWishlistRepository.save(accessoryWishlist);
        // Kafka -> MongoDB
        accessoryWishlist.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(accessoryWishlist);
    }

    //Operacia: Odstranenie wishlistu
    public void deleteWishlist(Integer id) {
        boolean exists = accessoryWishlistRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Unsuccessful DELETE request. Record with id: "+id+" is NOT exists!");
        }
        // Oracle
        accessoryWishlistRepository.deleteById(id);
        // Kafka -> MongoDB
        AccessoryWishlist accessoryWishlist = new AccessoryWishlist(id);
        accessoryWishlist.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(accessoryWishlist);
    }

    @Transactional
    public void updateWishlist(Integer id, String nazov, Integer uzivid, Integer accesoryId) {
        AccessoryWishlist wR = accessoryWishlistRepository.findById(id
        ).orElseThrow(() ->
                new IllegalStateException("Unsuccessful UPDATE request. Record with id: "+id+" is NOT exists!"));

        wR.setId(id);
        if (nazov != null && nazov.length() > 0){
            wR.setName(nazov);
        }
        if (uzivid != null){
            wR.setCustomerId(uzivid);
        }
        if (accesoryId != null){
            wR.setAccessoryId(accesoryId);
        }
        // Kafka -> MongoDB
        wR.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(wR);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(AccessoryWishlist accessoryWishlist) {
        try {
            String str_accessoryWishlist = OBJECT_MAPPER.writeValueAsString(accessoryWishlist);
            //SOT = Source of truth
            kafkaTemplate.send("accessoryWishlistService_SOT_event",  str_accessoryWishlist);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}

