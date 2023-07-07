package com.pdb_db.pdb_proj.tests.uzivatel;

import com.pdb_db.pdb_proj.domain.customer.Customer;
import com.pdb_db.pdb_proj.domain.customer.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
//@AutoConfigureMockMvc
//@DataJpaTest
class CustomerRepositoryTests
{
    @Autowired
    private CustomerRepository customerRepository;

    String email = "jarina@pdb.com";
    @Test
    void create_user()
    {

        Customer customer = new Customer("Jarka","Mala",email,"0911234567","Slovensko","Nitra", "Nabrezna", 9,91423);
        customerRepository.save(customer);

        boolean exists = false;
        if(customerRepository.findCustomerByEmail(email).isPresent())
            exists = true;

        assertThat(exists).isTrue();
    }

    @Test
    void no_user_no_email()
    {
        boolean exists = false;
        if(customerRepository.findCustomerByEmail("ABCDEF").isPresent())
            exists = true;

        assertThat(exists).isFalse();
    }
}
