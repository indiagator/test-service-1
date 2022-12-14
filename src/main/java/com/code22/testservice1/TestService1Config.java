package com.code22.testservice1;

import com.code22.testservice1.model.TestModelClass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConfigurationProperties(prefix = "service")
@Getter @Setter
public class TestService1Config{

    public String name;

    @Bean
    public WebClient.Builder loadBalancedWebClientBuilder()
    {
        return WebClient.builder();
    }

    @Bean
    public TestModelClass loadTestModelBean(TestModelClass modelClass)
    {
        return modelClass;
    }

}