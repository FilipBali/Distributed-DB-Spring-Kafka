package com.pdb_db.pdb_proj.domainMongo.customer_mongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customerM")
@AllArgsConstructor
public class CustomerControllerM
{
    private final CustomerServiceM customerServiceM;

    @GetMapping
    public List<CustomerM> fetchAllCustomerM()
    {
        return customerServiceM.getAllCustomerM();
    }
}
