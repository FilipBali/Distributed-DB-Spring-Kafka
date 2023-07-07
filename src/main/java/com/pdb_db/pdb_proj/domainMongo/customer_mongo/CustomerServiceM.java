package com.pdb_db.pdb_proj.domainMongo.customer_mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceM
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CustomerRepositoryM customerRepositoryM;
    public List<CustomerM> getAllCustomerM()
    {
        return customerRepositoryM.findAll();
    }

    @KafkaListener(topics = "customerService_SOT_event", groupId = "group-id")
    public void kafka_receiveMessage(String message) {
        try {
            Customer customer = OBJECT_MAPPER.readValue(message, Customer.class);

            // REST operation
            switch (customer.getOperation()){
                case PUT -> {
                    //Get uzivatel (oracleDB) id
                    Integer id = customer.getId();

                    //Find object by uzivatel (oracleDB) id in mongoDB
                    CustomerM customerM = customerRepositoryM.findById(id
                    ).orElseThrow(() ->
                            new IllegalStateException("Unsuccessful UPDATE request in MongoDB. Record with id: "+id+" is NOT exists!"));

                    // Change mongoDB object properties
                    customerM.setName(customer.getName());
                    customerM.setSurname(customer.getSurname());
                    customerM.setEmail(customer.getEmail());
                    customerM.setPhone_number(customer.getPhone_number());
                    customerM.setState(customer.getState());
                    customerM.setCity(customer.getCity());
                    customerM.setStreet_name(customer.getStreet_name());
                    customerM.setHouse_number(customer.getHouse_number());
                    customerM.setPostcode(customer.getPostcode());

                    // Save mongoDB object
                    customerRepositoryM.save(customerM);
                    break;
                }
                case GET -> {
                    break;
                }
                case POST -> {
                    // Create object and init properties
                    CustomerM customerM = new CustomerM(
                            customer.getId(),
                            customer.getName(),
                            customer.getSurname(),
                            customer.getEmail(),
                            customer.getPhone_number(),
                            customer.getState(),
                            customer.getCity(),
                            customer.getStreet_name(),
                            customer.getHouse_number(),
                            customer.getPostcode()
                    );
                    // Save object to MongoDB
                    customerRepositoryM.save(customerM);
                    break;
                }
                case DELETE -> {
                    // Create object and init id
                    CustomerM customerM = new CustomerM(
                            customer.getId()
                    );
                    // Delete object in MongoDB
                    customerRepositoryM.delete(customerM);
                    break;
                }
            }

        } catch (Exception e){
            System.out.println("Custom exception error [KafkaListener] " + e.getMessage());
        }
    }

}
