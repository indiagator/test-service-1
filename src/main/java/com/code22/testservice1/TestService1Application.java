package com.code22.testservice1;


import com.code22.testservice1.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient // Register itself with the Eureka Server
@RestController // This is not a Web App | This is a mini Web App or you can also called a Rest Microservice
@RequestMapping("api/0.1/test-service-1")
@CrossOrigin(origins = "http://localhost:8100/tradeblancco-1.0-SNAPSHOT")
public class TestService1Application {

    Logger logger = LoggerFactory.getLogger(TestService1Application.class);

    @Autowired
    public TestModelClass modelObject;

    @Autowired
    public TestService1Config config;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public static void main(String[] args) {

        SpringApplication.run(TestService1Application.class, args);

    }

    @GetMapping(value="/health", produces = "application/json")
    public Message getHealth()
    {
        Message testMsg = new Message();
        testMsg.setMsgString("Health A.OK! with " + config.getName());
        //ObjectMapper objectMapper = new ObjectMapper();

        logger.info("health was checked");

        return  testMsg; // Automatic Conversion of POJO to Json Object becuase of the @RestController
    }

    @PostMapping(value = "/save")
    public Message saveCustomer(@RequestBody Customer customer)
    {
        customerRepository.save(customer);

        // MQ related CODE


        Message saveCustomerMsg = new Message();
        saveCustomerMsg.setMsgString("New Customer Info Saved "+customer.getPhonenumber()+" "+customer.getName());


        return saveCustomerMsg;
    }

    @GetMapping(value = "/customer/{phonenumber}")
    public Customer getCustomerById(@PathVariable("phonenumber") String phonenumber)
    {

        modelObject.getSomeData();
        return (customerRepository.findById(phonenumber)).get();
    }

    @GetMapping(value = "/getAll")
    public List<Customer> getAllCustomers ()
    {

        logger.info("list of all customers were accessed");

        return (List<Customer>) customerRepository.findAll();
    }

    @GetMapping("/getAllKitchens")
    public Flux<Kitchen> getAllKitchens()
    {
        logger.info("list of all kitchens was accesed from test-service-2");
        return webClientBuilder.build().get().uri("http://localhost:8072/test-service-2/api/0.1/test-service-2/getAll").retrieve().bodyToFlux(Kitchen.class);
    }

}
