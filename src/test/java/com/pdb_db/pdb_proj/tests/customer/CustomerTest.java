package com.pdb_db.pdb_proj.tests.customer;

import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.domain.customer.CustomerRepository;
import com.pdb_db.pdb_proj.domain.customer.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest
{
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;


    @Test
    @Order(1)
    void createCustomer()
    {
        Customer customer = new Customer(
                "Jarka",
                "Mala",
                "jarina@pdb.com",
                "0911234567",
                "Slovensko",
                "Nitra",
                "Nabrezna",
                9,
                91423);
        service.addNewCustomer(customer);

        boolean exists = false;
        if(repository.findCustomerByEmail(customer.getEmail()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void getAllCustomers()
    {
         List<Customer> list = service.getCustomer();
        AtomicBoolean exists = new AtomicBoolean(false);

         list.forEach(l ->
         {
             if(repository.findCustomerByEmail(l.getEmail()).isPresent())
                 exists.set(true);
                 assertThat(exists.get()).isTrue();
                exists.set(false);
         });
    }

    @Test
    @Order(3)
    void getCustomer()
    {
        String email = "jarina@pdb.com";
        String name = "Jarka";
        String surname = "Mala";
        String state = "Slovensko";
        String city = "Nitra";

        Customer u = repository.findCustomerByEmail(email).get();

        assertThat(u.getEmail().equals(email)).isTrue();
        assertThat(u.getName().equals(name)).isTrue();
        assertThat(u.getSurname().equals(surname)).isTrue();
        assertThat(u.getState().equals(state)).isTrue();
        assertThat(u.getCity().equals(city)).isTrue();

    }

    @Test
    @Order(4)
    void changeCustomer()
    {
        String email = "jarina@pdb.com";
        String name = "Jarka Amelia";
        String surname = "Mala";
        String state = "Maďarsko";
        Integer house_number = 532;

        Customer u = repository.findCustomerByEmail(email).get();

        service.updateCustomer(
                u.getId(),
                name,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        service.updateCustomer(
                u.getId(),
                null,surname,
                null,
                null,state,
                null,null,
                null,
                null);

        service.updateCustomer(
                u.getId(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                house_number,
                null);

        u = repository.findCustomerByEmail(email).get();

        assertThat(u.getEmail().equals(email)).isTrue();
        assertThat(u.getName().equals(name)).isTrue();
        assertThat(u.getSurname().equals(surname)).isTrue();
        assertThat(u.getState().equals(state)).isTrue();
        assertThat(u.getHouse_number().equals(house_number)).isTrue();

        assertThat(u.getName().equals("Jarka")).isFalse();
        assertThat(u.getState().equals("Slovensko")).isFalse();

    }

    @Test
    @Order(5)
    void deleteCustomer()
    {
        String email = "jarina@pdb.com";
        boolean exists = false;
        Customer u = new Customer();

        if(repository.findCustomerByEmail(email).isPresent())
        {  exists = true;
          u = repository.findCustomerByEmail(email).get();
        }
        assertThat(exists).isTrue();

        exists = false;
        service.deleteCustomer(u.getId());
        if(repository.findCustomerByEmail(u.getEmail()).isPresent())
            exists = true;
        assertThat(exists).isFalse();
    }
}
