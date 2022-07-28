package com.code22.testservice1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("api/0.1/test-service-1")
public class TestService1Application {

    public static void main(String[] args) {
        SpringApplication.run(TestService1Application.class, args);
    }

    @GetMapping(value="/health", produces = "application/json")
    public Message getHealth()   {
        Message testMsg = new Message();
        testMsg.setMsgString("Health A.OK!");
        //ObjectMapper objectMapper = new ObjectMapper();
        return  testMsg;

    }

}
