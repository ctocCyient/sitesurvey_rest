package com.cyient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cyient.dao.TechnicianRepository;

@SpringBootApplication
public class SpringBootCrudRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudRestApplication.class, args);
       
    }
}