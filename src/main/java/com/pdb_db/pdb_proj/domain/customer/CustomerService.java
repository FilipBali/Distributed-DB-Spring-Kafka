package com.pdb_db.pdb_proj.domain.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservation;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservationRepository;
import com.pdb_db.pdb_proj.domain.accessory_reservation.AccessoryReservationService;
import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservation;
import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservationRepository;
import com.pdb_db.pdb_proj.domain.costume_reservation.CostumeReservationService;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlist;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlistRepository;
import com.pdb_db.pdb_proj.domain.accessory_wishlist.AccessoryWishlistService;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlist;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlistRepository;
import com.pdb_db.pdb_proj.domain.costume_wishlist.CostumeWishlistService;
import com.pdb_db.pdb_proj.utilities.rest_operationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final CustomerRepository customerRepository;

    // WishlistDoplnok
    private final AccessoryWishlistRepository accessoryWishlistRepository;
    private final AccessoryWishlistService accessoryWishlistService;

    // WishlistKostym
    private final CostumeWishlistRepository costumeWishlistRepository;
    private final CostumeWishlistService costumeWishlistService;

    // DoplnokRezervacia
    private final AccessoryReservationRepository accessoryReservationRepository;
    private final AccessoryReservationService accessoryReservationService;

    // KostymRezervacia
    private final CostumeReservationRepository costumeReservationRepository;
    private final CostumeReservationService costumeReservationService;


    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           // WishlistDoplnok
                           AccessoryWishlistRepository accessoryWishlistRepository,
                           AccessoryWishlistService accessoryWishlistService,
                           // WishlistKostym
                           CostumeWishlistRepository costumeWishlistRepository,
                           CostumeWishlistService costumeWishlistService,
                           // DoplnokRezervacia
                           AccessoryReservationRepository accessoryReservationRepository,
                           AccessoryReservationService accessoryReservationService,
                           // KostymRezervacia
                           CostumeReservationRepository costumeReservationRepository,
                           CostumeReservationService costumeReservationService

    ) {
        this.customerRepository = customerRepository;
        // WishlistDoplnok
        this.accessoryWishlistRepository = accessoryWishlistRepository;
        this.accessoryWishlistService = accessoryWishlistService;
        // WishlistKostym
        this.costumeWishlistRepository = costumeWishlistRepository;
        this.costumeWishlistService = costumeWishlistService;
        // DoplnokRezervacia
        this.accessoryReservationRepository = accessoryReservationRepository;
        this.accessoryReservationService = accessoryReservationService;
        // KostymRezervacia
        this.costumeReservationRepository = costumeReservationRepository;
        this.costumeReservationService = costumeReservationService;

    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }


    /*Operacia Vytvorit uzivatela*/
    public void addNewCustomer(Customer customer)
    {
        //Can't have new customer with same email
        Optional<Customer> uzivatelOptional =  customerRepository.findCustomerByEmail(customer.getEmail());

        if(uzivatelOptional.isPresent())
        {
            throw new IllegalStateException("Uzivatel with this email already exists");
        }

        // Oracle
        customerRepository.save(customer);
        // Kafka -> MongoDB
        customer.setOperation(rest_operationType.POST);
        this.kafka_sendMessage(customer);
    }

    /*Operacia: Zmazat uzivatela*/
    public void deleteCustomer(Integer id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Unsuccessful DELETE request. Record with id: "+id+" is NOT exists!");
        }

        // Oracle
        customerRepository.deleteById(id);
        // Kafka -> MongoDB
        Customer customer = new Customer(id);
        customer.setOperation(rest_operationType.DELETE);
        this.kafka_sendMessage(customer);

        // Cascade delete all customer's doplnok wishes
        List<AccessoryWishlist> accessoryWishlistList = accessoryWishlistRepository.findAllAccessoryWishlistsByCustomerId(id);
        for (AccessoryWishlist accessoryWishlist : accessoryWishlistList) {
            accessoryWishlistService.deleteWishlist(accessoryWishlist.getId());
        }


        // Cascade delete all customer's kostym wishes
        List<CostumeWishlist> costumeWishlistList = costumeWishlistRepository.findAllCostumeWishlistByCustomerId(id);
        for (CostumeWishlist costumeWishlist : costumeWishlistList) {
            costumeWishlistService.deleteCostumeWishlist(costumeWishlist.getId());
        }

        // Cascade delete all customer's doplnok reservations
        List<AccessoryReservation> accessoryReservationList = accessoryReservationRepository.findAllAccessoryReservationsByCustomerId(id);
        for (AccessoryReservation accessoryReservation : accessoryReservationList){
            accessoryReservationService.deleteAccessoryReservation(accessoryReservation.getId());
        }

        // Cascade delete all customer's kostym reservations
        List<CostumeReservation> costumeReservationList = costumeReservationRepository.findAllCostumeReservationsByCustomerId(id);
        for (CostumeReservation costumeReservation : costumeReservationList){
            costumeReservationService.deleteCostumeReservation(costumeReservation.getId());
        }

    }

    /*Operacia: Uprava profilu*/
    @Transactional
    public void updateCustomer(Integer id,
                               String meno,
                               String priezvisko,
                               String email,
                               String telefon,
                               String stat,
                               String mesto,
                               String ulica,
                               Integer cislodomu,
                               Integer psc ) {
        Customer uR = customerRepository.findById(id
        ).orElseThrow(() ->
                new IllegalStateException("Unsuccessful UPDATE request. Record with id: "+id+" is NOT exists!"));

        uR.setId(id);
        if (meno != null && meno.length() > 0){
            uR.setName(meno);
        }
        if (priezvisko != null && priezvisko.length() > 0){
            uR.setSurname(priezvisko);
        }
        if (email != null && email.length() > 0){
            uR.setEmail(email);
        }
        if (telefon != null && telefon.length() > 0){
            uR.setPhone_number(telefon);
        }
        if (stat != null && stat.length() > 0){
            uR.setState(stat);
        }
        if (mesto != null && mesto.length() > 0){
            uR.setCity(mesto);
        }
        if (ulica != null && ulica.length() > 0){
            uR.setStreet_name(ulica);
        }
        if (cislodomu != null){
            uR.setHouse_number(cislodomu);
        }
        if (psc != null){
            uR.setPostcode(psc);
        }

        // Kafka -> MongoDB
        uR.setOperation(rest_operationType.PUT);
        this.kafka_sendMessage(uR);
    }

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    public void kafka_sendMessage(Customer customer) {
        try {
            String str_customer = OBJECT_MAPPER.writeValueAsString(customer);
            //SOT = Source of truth
            kafkaTemplate.send("customerService_SOT_event", str_customer);
        } catch (Exception e){
            System.out.println("Custom exception error [KafkaSender]: " + e.getMessage());
        }

    }
}
