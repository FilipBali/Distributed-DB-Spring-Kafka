package com.pdb_db.pdb_proj.domain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
    }

    @PutMapping(path = "{id}")
    public void updateCustomer(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone_number,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street_name,
            @RequestParam(required = false) Integer house_number,
            @RequestParam(required = false) Integer postcode
    ){
        customerService.updateCustomer(id, name, surname, email, phone_number, state, city, street_name, house_number,
                postcode);
    }



}
