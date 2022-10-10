package com.code22.testservice1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("singleton")
@Getter @Setter
public class TestModelClass
{
    public String someData;
}
