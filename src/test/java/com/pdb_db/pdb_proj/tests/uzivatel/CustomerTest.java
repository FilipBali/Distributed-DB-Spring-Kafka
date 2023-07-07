package com.pdb_db.pdb_proj.tests.uzivatel;

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
    void create_uzivatel()
    {
        Customer customer = new Customer("Jarka","Mala","jarina@pdb.com","0911234567","Slovensko","Nitra", "Nabrezna", 9,91423);
        service.addNewCustomer(customer);

        boolean exists = false;
        if(repository.findCustomerByEmail(customer.getEmail()).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    @Order(2)
    void get_all_users()
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
    void get_uzivatel()
    {
        String email = "jarina@pdb.com";
        String meno = "Jarka";
        String priezvisko = "Mala";
        String stat = "Slovensko";
        String mesto = "Nitra";

        Customer u = repository.findCustomerByEmail(email).get();

        assertThat(u.getEmail().equals(email)).isTrue();
        assertThat(u.getName().equals(meno)).isTrue();
        assertThat(u.getSurname().equals(priezvisko)).isTrue();
        assertThat(u.getState().equals(stat)).isTrue();
        assertThat(u.getCity().equals(mesto)).isTrue();

    }

    @Test
    @Order(4)
    void change_user()
    {
        String email = "jarina@pdb.com";
        String meno = "Jarka Amelia";
        String priezvisko = "Mala";
        String stat = "Maďarsko";
        Integer cisloDomu = 532;

        Customer u = repository.findCustomerByEmail(email).get();

        service.updateCustomer(u.getId(),meno,null,null,null,null,null,null,null,null);
        service.updateCustomer(u.getId(),null,priezvisko,null,null,stat,null,null,null,null);
        service.updateCustomer(u.getId(),null,null,null,null,null,null,null,cisloDomu,null);

        u = repository.findCustomerByEmail(email).get();

        assertThat(u.getEmail().equals(email)).isTrue();
        assertThat(u.getName().equals(meno)).isTrue();
        assertThat(u.getSurname().equals(priezvisko)).isTrue();
        assertThat(u.getState().equals(stat)).isTrue();
        assertThat(u.getHouse_number().equals(cisloDomu)).isTrue();

        assertThat(u.getName().equals("Jarka")).isFalse();
        assertThat(u.getState().equals("Slovensko")).isFalse();

    }

    @Test
    @Order(5)
    void delete_user()
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
