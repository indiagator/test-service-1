package com.code22.testservice1;


import com.code22.testservice1.model.Customer;
import com.code22.testservice1.model.CustomerRepository;
import com.code22.testservice1.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/0.1/test-service-1")
public class TestService1Application {

    @Autowired
    public TestService1Config config;

    @Autowired
    public CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestService1Application.class, args);
    }

    @GetMapping(value="/health", produces = "application/json")
    public Message getHealth()
    {
        Message testMsg = new Message();
        testMsg.setMsgString("Health A.OK! with " + config.getName());
        //ObjectMapper objectMapper = new ObjectMapper();
        return  testMsg;
    }

    @PostMapping(value = "/save")
    public Message saveCustomer(@RequestBody Customer customer)
    {
        customerRepository.save(customer);
        Message saveCustomerMsg = new Message();
        saveCustomerMsg.setMsgString("New Customer Info Saved "+customer.getPhonenumber()+" "+customer.getName());
        return saveCustomerMsg;
    }

    @GetMapping(value = "/customer/{phonenumber}")
    public Customer getCustomerById(@PathVariable("phonenumber") String phonenumber)
    {
        return (customerRepository.findById(phonenumber)).get();
    }

    @GetMapping(value = "/getAll")
    public List<Customer> getAllCustomers ()
    {
        return (List<Customer>) customerRepository.findAll();
    }

}
